function registerGenre() {
    return new Promise (async (resolve) => {

        const name = document.getElementById("name").value.trim();
        const description = document.getElementById("description").value.trim();
    
        // Validaciones
        if (!name) {
            alert("Todos los campos obligatorios deben ser completados.");
            return;
        }
    
        if (name.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }
    
    
        if (description.length > 200) {
            alert("La descripción no puede tener más de 200 caracteres.");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"        
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": name,
            "description": description
        });

        let response = await fetch("http://127.0.0.1:8085/genres/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        // Limpiar los inputs
        document.getElementById("name").value = "";
        document.getElementById("description").value = "";
        getGenres();
        alert("Genre registered successfully!");
    });
}

function getGenres(){
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/genres/";
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
        var container = document.getElementById("listGenres");
        container.innerHTML = "";
        data.forEach(genre => {
            console.log(genre);

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
            title.innerText = genre["name"];

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            description.innerText = genre["description"];

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + genre.id + ")");
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deleteGenre(" + genre.id + ")");
            btnDelete.innerText = "Eliminar";

            buttonContainer.appendChild(btnEdit);
            buttonContainer.appendChild(btnDelete);

            // Construir card
            content.appendChild(title);
            content.appendChild(description);
            content.appendChild(buttonContainer);

            card.appendChild(content);
            col.appendChild(card);
            container.appendChild(col);
        });
    })
}

function deleteGenre(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this genre?");
        if (!confirmDelete) return;

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        const movieGenreUrl = `http://127.0.0.1:8085/movie_genre/genre/${id}`;

        const responseMovie = await fetch(movieGenreUrl, {
            method: "GET",
            headers: headersList
        });

        if (responseMovie.ok) {
            const relations = await responseMovie.json();
            
            if (relations.length > 0) {
                await fetch(movieGenreUrl, {
                    method: "DELETE",
                    headers: headersList
                });
                console.log("Relaciones eliminadas.");
            } else {
                console.log("No había relaciones para borrar.");
            }
        } else {
            console.log("No se pudo comprobar si había relaciones.");
        }

        let response = await fetch(`http://127.0.0.1:8085/genres/${id}`, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getGenres();
        alert("Genre deleted successfully!");
    })
}

let genreToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/genres/${id}`)
    .then(response => response.json())
    .then(genre => {
    genreToUpdate = genre;

    document.getElementById("update-name").value = genre.name;
    document.getElementById("update-description").value = genre.description;

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
        const updateName = document.getElementById("update-name").value.trim();
        const updatedescription = document.getElementById("update-description").value.trim();
    
        // Validaciones
        if (!updateName) {
            alert("Todos los campos obligatorios deben ser completados.");
            return;
        }
    
        if (updateName.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }
    
    
        if (updatedescription.length > 200) {
            alert("La descripción no puede tener más de 200 caracteres.");
            return;
        }
        
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            name: updateName,
            description: updatedescription
        });
    
        let response = await fetch(`http://127.0.0.1:8085/genres/${genreToUpdate.id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Genre updated successfully!");
            alert("Genre updated successfully!");
            closeModal();
            getGenres();
        } else {
            console.log("Error updating genre");
            alert("Error updating genre");
        }
    })
}