function registerPlatform() {
    return new Promise (async (resolve) => {

        const name = document.getElementById("name").value.trim();
        const urlWeb = document.getElementById("url_web").value.trim();

        // Validaciones
        if (!name || !urlWeb) {
            alert("El nombre y la URL son obligatorios.");
            return;
        }

        if (name.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }

        if (urlWeb.length > 200) {
            alert("La URL no puede tener más de 200 caracteres.");
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
            "url_web": urlWeb
        });

        let response = await fetch("http://127.0.0.1:8085/platforms/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        // Limpiar los inputs
        document.getElementById("name").value = "";
        document.getElementById("url_web").value = "";
        getPlatforms();
        alert("Platform registered successfully!");
    });
}

function getPlatforms(){
    return new Promise(async (resolve) => {
        var url = "http://127.0.0.1:8085/platforms/";
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
        var container = document.getElementById("listPlatforms");
        container.innerHTML = "";
        data.forEach(platform => {
            console.log(platform);

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
            title.innerText = platform["name"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">${platform["urlWeb"]}</span>`;

            // Botones
            let buttonContainer = document.createElement("div");
            buttonContainer.className = "movie-buttons";

            let btnEdit = document.createElement("button");
            btnEdit.className = "btn-edit";
            btnEdit.setAttribute("onclick", "openModal(" + platform.id + ")");
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
            btnDelete.setAttribute("onclick", "deletePlatform(" + platform.id + ")");
            btnDelete.innerText = "Eliminar";

            buttonContainer.appendChild(btnEdit);
            buttonContainer.appendChild(btnDelete);

            // Construir card
            content.appendChild(title);
            content.appendChild(info);
            content.appendChild(buttonContainer);

            card.appendChild(content);
            col.appendChild(card);
            container.appendChild(col);
        });
    })
}

function deletePlatform(id) {
    return new Promise(async (resolve) => {
        const confirmDelete = confirm("Are you sure you want to delete this platform?");
        if (!confirmDelete) return;

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(`http://127.0.0.1:8085/platforms/${id}`, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getPlatforms();
        alert("Platform deleted successfully!");
    })
}

let platformToUpdate = null;

function openModal(id) {
    fetch(`http://127.0.0.1:8085/platforms/${id}`)
    .then(response => response.json())
    .then(platform => {
    platformToUpdate = platform;

    document.getElementById("update-name").value = platform.name;
    document.getElementById("update-url_web").value = platform.urlWeb;

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
        const updateUrlWeb = document.getElementById("update-url_web").value.trim();

        // Validaciones
        if (!updateName || !updateUrlWeb) {
            alert("El nombre y la URL son obligatorios.");
            return;
        }

        if (updateName.length > 50) {
            alert("El nombre no puede tener más de 50 caracteres.");
            return;
        }

        if (updateUrlWeb.length > 200) {
            alert("La URL no puede tener más de 200 caracteres.");
            return;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            name: updateName,
            urlWeb: updateUrlWeb
        });
    
        let response = await fetch(`http://127.0.0.1:8085/platforms/${platformToUpdate.id}`, {
            method: "PUT",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
    
        if (response.ok) {
            console.log("Platform updated successfully!");
            alert("Platform updated successfully!");
            closeModal();
            getPlatforms();
        } else {
            console.log("Error updating platform");
            alert("Error updating platform");
        }    
    })
}