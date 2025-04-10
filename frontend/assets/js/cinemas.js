function registerCinema() {
    return new Promise(async(resolve) => {
        //alert("hola")
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("name").value,
            "address": document.getElementById("address").value,
            "phone": document.getElementById("phone").value
        });

        let response = await fetch("http://172.30.2.74:8085/cinemas/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });
        let data = await response.text();
        console.log(data);
        getCinemas();
    });
}

function getCinemas(){
    return new Promise(async (resolve) => {
        var url = "http://172.30.2.74:8085/cinemas/";
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
        var container = document.getElementById("listCinemas");
        container.innerHTML = "";
        data.forEach(cinema => {
            console.log(cinema);
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
            title.innerText = cinema["name"];

            let info = document.createElement("div");
            info.className = "movie-info";
            info.innerHTML = `<span id="card-year">Phone: ${cinema["phone"]}</span>`;

            let description = document.createElement("div");
            description.className = "movie-description";
            description.id = "card-description";
            description.innerText = cinema["address"];

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
            content.appendChild(info);
            content.appendChild(description);
            content.appendChild(buttonContainer);

            card.appendChild(content);
            col.appendChild(card);
            container.appendChild(col);
        });
    })
}