function registerGenre() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"        
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("name").value,
            "description": document.getElementById("description").value
        });

        let response = await fetch("http://localhost:8085/genres/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getGenres();
    });
}

function getGenres(){
    return new Promise(async (resolve) => {
        var url = "http://localhost:8085/genres/";
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
            btnEdit.innerText = "Editar";

            let btnDelete = document.createElement("button");
            btnDelete.className = "btn-delete";
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