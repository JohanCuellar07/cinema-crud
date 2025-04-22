function registerDirector() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("name").value,
            "url_image": document.getElementById("url_image").value,
            "birth": document.getElementById("birth").value,
            "nationality": document.getElementById("nationality").value
        });

        let response = await fetch("http://127.0.0.1:8085/directors/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getDirectors();
    });
}

function getDirectors(){
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/directors/";
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
        var container = document.getElementById("listDirectors");
        container.innerHTML = "";
        data.forEach(director => {
            console.log(director);

            let col = document.createElement("div");
            col.className = "col-lg-3 col-md-4 col-sm-6 d-flex justify-content-center";

            // Card principal
            let card = document.createElement("div");
            card.className = "movie-card";

            // Imagen
            let imageContainer = document.createElement("div");
            imageContainer.className = "movie-image";

            let image = document.createElement("img");
            image.src = director["urlImage"];
            image.alt = "Imagen del director";
            image.id = "card-image";

            imageContainer.appendChild(image);

            // Contenido
            let content = document.createElement("div");
            content.className = "movie-content";

            let title = document.createElement("div");
            title.className = "movie-title";
            title.id = "card-title";
            title.innerText = director["name"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">${director["birth"]}</span> • <span id="card-time">${director["nationality"]}</span>`;

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            //description.innerText = director["biography"];

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + director.id + ")");
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deleteMovie(" + director.id + ")");
            btnDelete.innerText = "Eliminar";

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

function deleteDirector(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this director?");
        if (!confirmDelete) return;
        var url = `http://127.0.0.1:8085/directors/${id}`;

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
        getDirectors();
    })
}

let directorToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/directors/${id}`)
    .then(response => response.json())
    .then(director => {
    directorToUpdate = director;

    document.getElementById("update-name").value = director.name;
    document.getElementById("update-url_image").value = director.urlImage;
    document.getElementById("update-birth").value = director.birth;
    document.getElementById("update-nationality").value = director.nationality;

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
        const updatedDirector = {
            name: document.getElementById("update-name").value,
            urlImage: document.getElementById("update-url_image").value,
            birth: document.getElementById("update-birth").value,
            nationality: document.getElementById("update-nationality").value
        };
        
        var url = `http://127.0.0.1:8085/directors/${directorToUpdate.id}`;
        console.log(url);
        
        let headersList = {
                "Accept": "*/*",
                "User-Agent": "web",
                "Content-Type": "application/json"
            }
    
        let response = await fetch(url, {
            method: "PUT",
            body: JSON.stringify(updatedDirector),
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Director updated successfully!");
            closeModal();
            getDirectors();
        } else {
            console.log("Error updating director");
        }
    })
}