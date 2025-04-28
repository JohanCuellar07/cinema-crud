function registerDirector() {
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

        let response = await fetch("http://127.0.0.1:8085/directors/", {
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
        getDirectors();
        alert("Director registered successfully!");
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
            btnDelete.setAttribute("onclick", "deleteDirector(" + director.id + ")");
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

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(`http://127.0.0.1:8085/directors/${id}`, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getDirectors();
        alert("Director deleted successfully!");
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
    
        let response = await fetch(`http://127.0.0.1:8085/directors/${directorToUpdate.id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Director updated successfully!");
            alert("Director updated successfully!");
            closeModal();
            getDirectors();
        } else {
            console.log("Error updating director");
            alert("Error updating director");
        }
    })
}