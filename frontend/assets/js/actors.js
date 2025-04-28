function registerActor() {
    return new Promise (async (resolve) => {

        const name = document.getElementById("name").value.trim();
        const urlImage = document.getElementById("url_image").value.trim();
        const birth = document.getElementById("birth").value.trim();
        const nationality = document.getElementById("nationality").value.trim();

        // Validaciones
        if (!name || !urlImage) {
            alert("El nombre y la URL de la imagen son obligatorios.");
            return;
        }

        if (name.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }

        if (urlImage.length > 200) {
            alert("La URL de la imagen no puede tener más de 200 caracteres.");
            return;
        }

        const today = new Date().toISOString().split('T')[0]; // Formato yyyy-mm-dd

        if (birth > today) {
            alert("Birth date cannot be in the future.");
            return;
        }

        if (nationality.length > 50) {
            alert("La nacionalidad no puede tener más de 50 caracteres.");
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
            "url_image": urlImage,
            "birth": birth,
            "nationality": nationality
        });

        let response = await fetch("http://127.0.0.1:8085/actors/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        // Limpiar los inputs
        document.getElementById("name").value = "";
        document.getElementById("url_image").value = "";
        document.getElementById("birth").value = "";
        document.getElementById("nationality").value = "";
        getActors();
        alert("Actor registered successfully!");
    });
}

function getActors(){
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/actors/";
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
        var container = document.getElementById("listActors");
        container.innerHTML = "";
        data.forEach(actor => {
            console.log(actor);

            let col = document.createElement("div");
            col.className = "col-lg-3 col-md-4 col-sm-6 d-flex justify-content-center";

            // Card principal
            let card = document.createElement("div");
            card.className = "movie-card";

            // Imagen
            let imageContainer = document.createElement("div");
            imageContainer.className = "movie-image";

            let image = document.createElement("img");
            image.src = actor["urlImage"];
            image.alt = "Imagen del actor o actriz";
            image.id = "card-image";

            imageContainer.appendChild(image);

            // Contenido
            let content = document.createElement("div");
            content.className = "movie-content";

            let title = document.createElement("div");
            title.className = "movie-title";
            title.id = "card-title";
            title.innerText = actor["name"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">${actor["birth"]}</span> • <span id="card-time">${actor["nationality"]}</span>`;

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            //description.innerText = actor["biography"];

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + actor.id + ")");
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deleteActor(" + actor.id + ")");
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

function deleteActor(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this actor?");
        if (!confirmDelete) return;

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(`http://127.0.0.1:8085/actors/${id}`, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getActors();
        alert("Actor deleted successfully!");
    })
}

let actorToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/actors/${id}`)
    .then(response => response.json())
    .then(actor => {
    actorToUpdate = actor;

    document.getElementById("update-name").value = actor.name;
    document.getElementById("update-url_image").value = actor.urlImage;
    document.getElementById("update-birth").value = actor.birth;
    document.getElementById("update-nationality").value = actor.nationality;

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
        const updateUrlImage = document.getElementById("update-url_image").value.trim();
        const updateBirth = document.getElementById("update-birth").value.trim();
        const updateNationality = document.getElementById("update-nationality").value.trim();

        // Validaciones
        if (!updateName || !updateUrlImage) {
            alert("El nombre y la URL de la imagen son obligatorios.");
            return;
        }

        if (updateName.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }

        if (updateUrlImage.length > 200) {
            alert("La URL de la imagen no puede tener más de 200 caracteres.");
            return;
        }

        const today = new Date().toISOString().split('T')[0]; // Formato yyyy-mm-dd

        if (updateBirth > today) {
            alert("Birth date cannot be in the future.");
            return;
        }

        if (updateNationality.length > 50) {
            alert("La nacionalidad no puede tener más de 50 caracteres.");
            return;
        }
        
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            name: updateName,
            urlImage: updateUrlImage,
            birth: updateBirth,
            nationality: updateNationality
        });
    
        let response = await fetch(`http://127.0.0.1:8085/actors/${actorToUpdate.id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Actor updated successfully!");
            alert("Actor updated successfully!");
            closeModal();
            getActors();
        } else {
            console.log("Error updating actor");
            alert("Error updating actor");
        }
    })
}