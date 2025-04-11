function registerReview() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "movie_id": document.getElementById("movie_id").value,
            "name_reviewer": document.getElementById("name_reviewer").value,
            "rating": document.getElementById("rating").value,
            "comment": document.getElementById("comment").value
        });
        let response = await fetch("http://172.30.2.74:8085/reviews/", {
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
        var url = "http://172.30.2.74:8085/reviews/";
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
        });
    })
}

async function loadMovies() {
    try {
        const response = await fetch("http://172.30.2.74:8085/movies/");
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