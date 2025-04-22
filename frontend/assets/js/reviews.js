function registerReview() {
    return new Promise (async (resolve) => {
        
        const movieId = {"id": parseInt(document.getElementById("movie_id").value)};
        const nameReviewer = document.getElementById("name_reviewer").value.trim();
        const rating = document.getElementById("rating").value.trim();
        const comment = document.getElementById("comment").value.trim();

        // Validaciones
        if (!movieId || !nameReviewer || !rating || !comment) {
            alert("Todos los campos obligatorios deben ser completados.");
            return;
        }

        if (nameReviewer.length > 50) {
            alert("El nombre del revisor no puede tener más de 50 caracteres.");
            return;
        }

        if (rating > 5) {
            alert("La calificación no puede ser mayor a 5.");
            return;
        }

        if (comment.length > 150) {
            alert("El comentario no puede tener más de 150 caracteres.");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "movie": movieId,
            "name_reviewer": nameReviewer,
            "rating": rating,
            "comment": comment
        });
        let response = await fetch("http://127.0.0.1:8085/reviews/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });
        let data = await response.text();
        console.log(data);
        getReviews();
    });
}

function getReviews(){
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/reviews/";
        /*
        var filtro = document.getElementById("nameFilter").value;
        if (filtro != "") {
            url += "filter/" + filtro;
        }
        */
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(url, {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        var container = document.getElementById("listReviews");
        container.innerHTML = "";
        data.forEach(review => {
            console.log(review);
            let col = document.createElement("div");
            col.className = "col-lg-3 col-md-4 col-sm-6 d-flex justify-content-center";

            // Card principal
            let card = document.createElement("div");
            card.className = "movie-card";

            // Contenido
            let content = document.createElement("div");
            content.className = "movie-content";

            let title = document.createElement("div");
            title.className = "movie-title";
            title.id = "card-title";
            title.innerText = review["movie_id"]["title"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">${review["nameReviewer"]}</span> • <span id="card-time">${review["rating"]}</span>`;

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            description.innerText = review["comment"];

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + review.id + ")");
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deleteReview(" + review.id + ")");
            btnDelete.innerText = "Eliminar";

            buttonContainer.appendChild(btnEdit);
            buttonContainer.appendChild(btnDelete);

            // Construir card
            content.appendChild(title);
            content.appendChild(info);
            content.appendChild(description);
            content.appendChild(buttonContainer);

            card.appendChild(content);
            col.appendChild(card);
            container.appendChild(col);
        });
    })
}

function deleteReview(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this review?");
        if (!confirmDelete) return;
        var url = `http://127.0.0.1:8085/reviews/${id}`;

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(url, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getReviews();
    })
}

let reviewToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/reviews/${id}`)
    .then(response => response.json())
    .then(review => {
    reviewToUpdate = review;

    document.getElementById("update-movie_id").value = review.movie_id.title;
    document.getElementById("update-name_reviewer").value = review.nameReviewer;
    document.getElementById("update-rating").value = review.rating;
    document.getElementById("update-comment").value = review.comment;

    document.getElementById("updateModal").style.display = "block";
    })
    .catch(error => {
    console.error("Error al obtener la película:", error);
    alert("Error al cargar la película para editar.");
    });
}

function closeModal() {
  document.getElementById("updateModal").style.display = "none";
}

function submitUpdate() {
    return new Promise(async (resolve) => {
        const updatedReview = {
            
            movie: reviewToUpdate.movie_id,
            name_reviewer: document.getElementById("update-name_reviewer").value,
            rating: parseInt(document.getElementById("update-rating").value),
            comment: document.getElementById("update-comment").value
        };
        
        var url = `http://127.0.0.1:8085/reviews/${reviewToUpdate.id}`;
        console.log(url);
        
        let headersList = {
                "Accept": "*/*",
                "User-Agent": "web",
                "Content-Type": "application/json"
            }
    
        let response = await fetch(url, {
            method: "PUT",
            body: JSON.stringify(updatedReview),
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Review updated successfully!");
            closeModal();
            getReviews();
        } else {
            console.log("Error updating review");
        }
    })
}

async function loadMovies() {
    try {
        const response = await fetch("http://127.0.0.1:8085/movies/");
        const movies = await response.json();

        const select = document.getElementById("movie_id");
        select.innerHTML = ""; // Limpiar opciones anteriores

        // Agregar cada género como opción
        movies.forEach(movie => {
            const option = document.createElement("option");
            option.value = movie.id; // Asegúrate de que sea el ID del género
            option.textContent = movie.title; // O el campo que contenga el nombre
            select.appendChild(option);
        });

        // Inicializar Choices (si ya estaba creado, lo destruimos primero)
        if (select.choicesInstance) {
            select.choicesInstance.destroy();
        }

        const choices = new Choices(select, {
            searchEnabled: true,
            itemSelectText: '',
        });

        // Guardamos la instancia para evitar múltiples inicializaciones
        select.choicesInstance = choices;

    } catch (error) {
        console.error("Error cargando géneros:", error);
    }
}

// Llamar a la función cuando cargue la página
window.addEventListener("DOMContentLoaded", loadMovies);