function registerMovie() {
    return new Promise(async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        };

        const selectedGenres = Array.from(document.getElementById("generos").selectedOptions).map(option => parseInt(option.value));

        let bodyContent = JSON.stringify({
            "id": 0,
            "title": document.getElementById("title").value,
            "url_image": document.getElementById("url_image").value,
            "description": document.getElementById("description").value,
            "time_min": document.getElementById("time_min").value,
            "launch_year": document.getElementById("launch_year").value
        });

        // Paso 1: Obtener lista de películas antes de insertar
        const beforeResponse = await fetch("http://127.0.0.1:8085/movies/", {
            method: "GET",
            headers: headersList
        });
        const beforeMovies = await beforeResponse.json();
        const beforeIds = beforeMovies.map(m => m.id);

        // Paso 2: Crear la película
        await fetch("http://127.0.0.1:8085/movies/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        // Paso 3: Obtener lista después de insertar
        const afterResponse = await fetch("http://127.0.0.1:8085/movies/", {
            method: "GET",
            headers: headersList
        });
        const afterMovies = await afterResponse.json();

        // Paso 4: Buscar el nuevo ID
        const newMovie = afterMovies.find(m => !beforeIds.includes(m.id));

        if (!newMovie) {
            console.error("No se pudo identificar la nueva película.");
            return;
        }

        const movieId = newMovie.id;

        // Paso 5: Enlazar géneros
        for (const genreId of selectedGenres) {
            const movieGenreBody = JSON.stringify({
                movie: { id: movieId },
                genre: { id: genreId }
            });

            const responseMovieGenre = await fetch("http://127.0.0.1:8085/movie_genre/", {
                method: "POST",
                body: movieGenreBody,
                headers: headersList
            });

            const dataMovieGenre = await responseMovieGenre.text();
            console.log(dataMovieGenre);
        }

        getMovies();
    });
}

function getMovies() {
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/movies/";
        const filterType = document.getElementById("filterType").value;
        const filterValue = document.getElementById("nameFilter").value;
    
        if (filterValue !== "") {
            url += `filter/${filterType}/${filterValue}`;
        }
        
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
        var container = document.getElementById("listMovies");
        container.innerHTML = "";
        data.forEach(movie => {
            // Crear columna si estás usando Bootstrap o grillas
            let col = document.createElement("div");
            col.className = "col-lg-3 col-md-4 col-sm-6 d-flex justify-content-center";

            // Card principal
            let card = document.createElement("div");
            card.className = "movie-card";

            // Imagen
            let imageContainer = document.createElement("div");
            imageContainer.className = "movie-image";

            let image = document.createElement("img");
            image.src = movie["urlImage"];
            image.alt = "Imagen de la película";
            image.id = "card-image";

            imageContainer.appendChild(image);

            // Contenido
            let content = document.createElement("div");
            content.className = "movie-content";

            let title = document.createElement("div");
            title.className = "movie-title";
            title.id = "card-title";
            title.innerText = movie["title"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">${movie["launch_year"]}</span> • <span id="card-time">${movie["time_min"]}</span> min`;

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            description.innerText = movie["description"];

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + movie.id + ")");
            btnEdit.innerText = "Update";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deleteMovie(" + movie.id + ")");
            btnDelete.innerText = "Delete";

            buttonContainer.appendChild(btnEdit);
            buttonContainer.appendChild(btnDelete);

            // Construir card
            content.appendChild(title);
            content.appendChild(info);
            content.appendChild(description);
            content.appendChild(buttonContainer);

            card.appendChild(imageContainer);
            card.appendChild(content);
            col.appendChild(card);
            container.appendChild(col);
        });
    })
}

function deleteMovie(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this movie?");
        if (!confirmDelete) return;
        var url = `http://127.0.0.1:8085/movies/${id}`;

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
        getMovies();
    })
}

let movieToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/movies/${id}`)
    .then(response => response.json())
    .then(movie => {
    movieToUpdate = movie;

    document.getElementById("update-title").value = movie.title;
    document.getElementById("update-url_image").value = movie.urlImage;
    document.getElementById("update-description").value = movie.description;
    document.getElementById("update-time_min").value = movie.time_min;
    document.getElementById("update-launch_year").value = movie.launch_year;

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
        const updatedMovie = {
            title: document.getElementById("update-title").value,
            urlImage: document.getElementById("update-url_image").value,
            description: document.getElementById("update-description").value,
            time_min: parseInt(document.getElementById("update-time_min").value),
            launch_year: document.getElementById("update-launch_year").value
        };
        
        var url = `http://127.0.0.1:8085/movies/${movieToUpdate.id}`;
        console.log(url);
        
        let headersList = {
                "Accept": "*/*",
                "User-Agent": "web",
                "Content-Type": "application/json"
            }
    
        let response = await fetch(url, {
            method: "PUT",
            body: JSON.stringify(updatedMovie),
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Movie updated successfully!");
            closeModal();
            getMovies();
        } else {
            console.log("Error updating movie");
        }
    })
}

async function loadGenres() {
    try {
        const response = await fetch("http://127.0.0.1:8085/genres/");
        const genres = await response.json();

        const select = document.getElementById("generos");
        select.innerHTML = ""; // Limpiar opciones anteriores

        // Agregar cada género como opción
        genres.forEach(genre => {
            const option = document.createElement("option");
            option.value = genre.id; // Asegúrate de que sea el ID del género
            option.textContent = genre.name; // O el campo que contenga el nombre
            select.appendChild(option);
        });

        // Inicializar Choices (si ya estaba creado, lo destruimos primero)
        if (select.choicesInstance) {
            select.choicesInstance.destroy();
        }

        const choices = new Choices(select, {
            removeItemButton: true
        });

        // Guardamos la instancia para evitar múltiples inicializaciones
        select.choicesInstance = choices;

    } catch (error) {
        console.error("Error cargando géneros:", error);
    }
}

// Llamar a la función cuando cargue la página
window.addEventListener("DOMContentLoaded", loadGenres);