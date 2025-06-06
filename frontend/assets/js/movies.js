function registerMovie() {
    return new Promise(async (resolve) => {
        // Obtener valores
        const title = document.getElementById("title").value.trim();
        const urlImage = document.getElementById("url_image").value.trim();
        const description = document.getElementById("description").value.trim();
        const timeMin = document.getElementById("time_min").value.trim();
        const launchYear = document.getElementById("launch_year").value.trim();
        const selectedGenres = Array.from(document.getElementById("generos").selectedOptions).map(option => parseInt(option.value));
        const selectedActors = Array.from(document.getElementById("actores").selectedOptions).map(option => parseInt(option.value));
        const selectedDirectors = Array.from(document.getElementById("directores").selectedOptions).map(option => parseInt(option.value));
        const selectedPlatforms = Array.from(document.getElementById("plataformas").selectedOptions).map(option => parseInt(option.value));

        // Validaciones
        if (!title || !urlImage || !timeMin || !launchYear) {
            alert("Todos los campos obligatorios deben ser completados.");
            return;
        }

        if (title.length > 50) {
            alert("El título no puede tener más de 50 caracteres.");
            return;
        }

        if (urlImage.length > 200) {
            alert("La URL de la imagen no puede tener más de 200 caracteres.");
            return;
        }

        if (description.length > 200) {
            alert("La descripción no puede tener más de 200 caracteres.");
            return;
        }

        const today = new Date().toISOString().split('T')[0]; // Formato yyyy-mm-dd

        if (launchYear > today) {
            alert("Launch year cannot be in the future.");
            return;
        }

        const parsedTime = parseInt(timeMin);
        if (isNaN(parsedTime) || parsedTime <= 0) {
            alert("El tiempo debe ser un número mayor a 0.");
            return;
        }

        if (selectedGenres.length === 0) {
            alert("Debes seleccionar al menos un género.");
            return;
        }

        if (selectedActors.length === 0) {
            alert("Debes seleccionar al menos un actor.");
            return;
        }

        if (selectedDirectors.length === 0) {
            alert("Debes seleccionar al menos un director.");
            return;
        }

        if (selectedPlatforms.length === 0) {
            alert("Debes seleccionar al menos una plataforma.");
            return;
        }

        // Continuar con el registro si pasa todas las validaciones
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        };

        let bodyContent = JSON.stringify({
            "id": 0,
            "title": title,
            "url_image": urlImage,
            "description": description,
            "time_min": parsedTime,
            "launch_year": launchYear
        });

        const beforeResponse = await fetch("http://127.0.0.1:8085/movies/", {
            method: "GET",
            headers: headersList
        });
        const beforeMovies = await beforeResponse.json();
        const beforeIds = beforeMovies.map(m => m.id);

        await fetch("http://127.0.0.1:8085/movies/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        const afterResponse = await fetch("http://127.0.0.1:8085/movies/", {
            method: "GET",
            headers: headersList
        });
        const afterMovies = await afterResponse.json();
        const newMovie = afterMovies.find(m => !beforeIds.includes(m.id));

        if (!newMovie) {
            console.error("No se pudo identificar la nueva película.");
            return;
        }

        const movieId = newMovie.id;

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

        for (const actorId of selectedActors) {
            const movieActorBody = JSON.stringify({
                movie: { id: movieId },
                actor: { id: actorId }
            });

            const responseMovieActor = await fetch("http://127.0.0.1:8085/movie_actor/", {
                method: "POST",
                body: movieActorBody,
                headers: headersList
            });

            const dataMovieActor = await responseMovieActor.text();
            console.log(dataMovieActor);
        }

        for (const directorId of selectedDirectors) {
            const movieDirectorBody = JSON.stringify({
                movie: { id: movieId },
                director: { id: directorId }
            });

            const responseMovieDirector = await fetch("http://127.0.0.1:8085/movie_director/", {
                method: "POST",
                body: movieDirectorBody,
                headers: headersList
            });

            const dataMovieDirector = await responseMovieDirector.text();
            console.log(dataMovieDirector);
        }

        for (const platformId of selectedPlatforms) {
            const moviePlatformBody = JSON.stringify({
                movie: { id: movieId },
                platform: { id: platformId }
            });

            const responseMoviePlatform = await fetch("http://127.0.0.1:8085/movie_platform/", {
                method: "POST",
                body: moviePlatformBody,
                headers: headersList
            });

            const dataMoviePlatform = await responseMoviePlatform.text();
            console.log(dataMoviePlatform);
        }

        // Limpiar los inputs
        document.getElementById("title").value = "";
        document.getElementById("url_image").value = "";
        document.getElementById("description").value = "";
        document.getElementById("time_min").value = "";
        document.getElementById("launch_year").value = "";
        getMovies();
        alert("Movie registered successfully!");
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
            // Crear columna
            let col = document.createElement("div");
            col.className = "col-lg-3 col-md-4 col-sm-6 d-flex justify-content-center";
            
            // Card principal
            let card = document.createElement("div");
            card.className = "movie-card";
        
            // Imagen
            let imageContainer = document.createElement("div");
            imageContainer.className = "movie-image";
        
            let linkImage = document.createElement("a"); // link para la imagen
            linkImage.href = "movieDetails.html?id=" + movie.id;
            linkImage.style.textDecoration = "none";
            linkImage.style.color = "inherit";
        
            let image = document.createElement("img");
            image.src = movie["urlImage"];
            image.alt = "Imagen de la película";
            image.id = "card-image";
        
            // Contenido
            let content = document.createElement("div");
            content.className = "movie-content";
        
            let linkTitle = document.createElement("a"); // link para el título
            linkTitle.href = "movieDetails.html?id=" + movie.id;
            linkTitle.style.textDecoration = "none";
            linkTitle.style.color = "inherit";
        
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
            linkImage.appendChild(image); // la imagen dentro del link
            imageContainer.appendChild(linkImage);
            linkTitle.appendChild(title); // el título dentro del link
            content.appendChild(linkTitle); // usamos linkTitle (no directamente title)
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

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        };

        // 1. Eliminar relaciones en la tabla pivote
        const movieGenreUrl = `http://127.0.0.1:8085/movie_genre/movie/${id}`;
        await fetch(movieGenreUrl, {
            method: "DELETE",
            headers: headersList
        });

        const movieActorUrl = `http://127.0.0.1:8085/movie_actor/movie/${id}`;
        await fetch(movieActorUrl, {
            method: "DELETE",
            headers: headersList
        });

        const movieDirectorUrl = `http://127.0.0.1:8085/movie_director/movie/${id}`;
        await fetch(movieDirectorUrl, {
            method: "DELETE",
            headers: headersList
        });

        const moviePlatformUrl = `http://127.0.0.1:8085/movie_platform/movie/${id}`;
        await fetch(moviePlatformUrl, {
            method: "DELETE",
            headers: headersList
        });

        // 2. Eliminar la película
        const movieUrl = `http://127.0.0.1:8085/movies/${id}`;
        let response = await fetch(movieUrl, {
            method: "DELETE",
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getMovies();
        alert("Movie deleted successfully!");
    });
}

let movieToUpdate = null;

async function openModal(id) {
    try {
        // Obtén la película con sus géneros a través de la API
        const response = await fetch(`http://127.0.0.1:8085/movies/${id}`);
        const movie = await response.json();
        movieToUpdate = movie;

        // Primero carga los géneros en el select del modal
        await loadUpdateGenres(movie.generos ? movie.generos.map(g => g.id) : []);

        // Ahora puedes continuar llenando los campos del formulario del modal
        document.getElementById("update-title").value = movie.title;
        document.getElementById("update-url_image").value = movie.urlImage;
        document.getElementById("update-description").value = movie.description;
        document.getElementById("update-time_min").value = movie.time_min;
        document.getElementById("update-launch_year").value = movie.launch_year;

        // El select de géneros es el que se ha cargado
        const generosSelect = document.getElementById("update-generos");

        // Primero deseleccionamos todas las opciones
        Array.from(generosSelect.options).forEach(option => {
            option.selected = false;
        });

        // Seleccionamos los géneros de la película (de la pivote)
        if (movie.generos && Array.isArray(movie.generos)) {
            movie.generos.forEach(genero => {
                const optionToSelect = Array.from(generosSelect.options).find(option => parseInt(option.value) === genero.id);
                if (optionToSelect) {
                    optionToSelect.selected = true;
                }
            });
        }

        // Elimina instancias anteriores de Choices si existen
        if (generosSelect.choicesInstance) {
            generosSelect.choicesInstance.destroy();
        }

        // Crea nueva instancia Choices para el select de géneros
        const choices = new Choices(generosSelect, {
            removeItemButton: true
        });
        generosSelect.choicesInstance = choices;

        // Mostrar el modal
        document.getElementById("updateModal").style.display = "block";
    } catch (error) {
        console.error("Error al obtener la película:", error);
        alert("Error al cargar la película para editar.");
    }
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
            alert("Movie updated successfully!");
            closeModal();
            getMovies();
        } else {
            console.log("Error updating movie");
            alert("Error updating movie");
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

async function loadActors() {
    try {
        const response = await fetch("http://127.0.0.1:8085/actors/");
        const actors = await response.json();

        const select = document.getElementById("actores");
        select.innerHTML = ""; // Limpiar opciones anteriores

        // Agregar cada género como opción
        actors.forEach(actor => {
            const option = document.createElement("option");
            option.value = actor.id; // Asegúrate de que sea el ID del género
            option.textContent = actor.name; // O el campo que contenga el nombre
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
        console.error("Error cargando actores:", error);
    }
}

async function loadDirectors() {
    try {
        const response = await fetch("http://127.0.0.1:8085/directors/");
        const directors = await response.json();

        const select = document.getElementById("directores");
        select.innerHTML = ""; // Limpiar opciones anteriores

        // Agregar cada género como opción
        directors.forEach(director => {
            const option = document.createElement("option");
            option.value = director.id; // Asegúrate de que sea el ID del género
            option.textContent = director.name; // O el campo que contenga el nombre
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
        console.error("Error cargando directores:", error);
    }
}

async function loadPlatforms() {
    try {
        const response = await fetch("http://127.0.0.1:8085/platforms/");
        const platforms = await response.json();

        const select = document.getElementById("plataformas");
        select.innerHTML = ""; // Limpiar opciones anteriores

        // Agregar cada género como opción
        platforms.forEach(platform => {
            const option = document.createElement("option");
            option.value = platform.id; // Asegúrate de que sea el ID del género
            option.textContent = platform.name; // O el campo que contenga el nombre
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
        console.error("Error cargando plataformas:", error);
    }
}

async function loadUpdateGenres(selectedGenreIds) {
    try {
        const response = await fetch("http://127.0.0.1:8085/genres/");
        const genres = await response.json();

        const select = document.getElementById("update-generos");
        select.innerHTML = "";

        genres.forEach(genre => {
            const option = document.createElement("option");
            option.value = genre.id;
            option.text = genre.name;
            if (selectedGenreIds.includes(genre.id)) {
                option.selected = true;
            }
            select.appendChild(option);
        });
    } catch (error) {
        console.error("Error loading genres for update:", error);
    }
}
