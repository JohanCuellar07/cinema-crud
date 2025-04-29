function getMovie() {
    return new Promise(async (resolve) => {
        const movieId = new URLSearchParams(window.location.search).get('id');
        const response = await fetch(`http://127.0.0.1:8085/movies/${movieId}`);
        const movie = await response.json();


        document.getElementById("movie-title").innerText = movie.title;
        document.getElementById("movie-image").src = movie.urlImage;
        document.getElementById("movie-description").innerText = movie.description;
        document.getElementById("movie-duration").innerText = movie.time_min;
        document.getElementById("movie-launch").innerText = movie.launch_year;
        

        const genresResponse = await fetch(`http://127.0.0.1:8085/movies/genres/${movieId}`);
        const genresData = await genresResponse.json();


        const genresContainer = document.getElementById("movie-genres");
        genresContainer.innerHTML = ""; // Limpiamos por si acaso

        if (genresData.length > 0) {
            genresData.forEach(genre => {
                const genreElement = document.createElement("span");
                genreElement.innerText = genre.name;
                genreElement.classList.add("genre-tag");
                genresContainer.appendChild(genreElement);
            });
        } else {
            genresContainer.innerText = "No genres available.";
        }

        const actorsResponse = await fetch(`http://127.0.0.1:8085/movies/actors/${movieId}`);
        const actorsData = await actorsResponse.json();

        const actorsContainer = document.getElementById("movie-actors");
        actorsContainer.innerHTML = ""; // Limpiamos por si acaso

        if (actorsData.length > 0) {
            actorsData.forEach(actor => {
                const actorElement = document.createElement("span");
                actorElement.innerText = actor.name;
                actorElement.classList.add("genre-tag");
                actorsContainer.appendChild(actorElement);
            });
        } else {
            actorsContainer.innerText = "No actors available.";
        }

        const directorsResponse = await fetch(`http://127.0.0.1:8085/movies/directors/${movieId}`);
        const directorsData = await directorsResponse.json();

        const directorsContainer = document.getElementById("movie-directors");
        directorsContainer.innerHTML = ""; // Limpiamos por si acaso

        if (directorsData.length > 0) {
            directorsData.forEach(director => {
                const directorElement = document.createElement("span");
                directorElement.innerText = director.name;
                directorElement.classList.add("genre-tag");
                directorsContainer.appendChild(directorElement);
            });
        } else {
            directorsContainer.innerText = "No directors available.";
        }

        const platformsResponse = await fetch(`http://127.0.0.1:8085/movies/platforms/${movieId}`);
        const platformsData = await platformsResponse.json();

        const platformsContainer = document.getElementById("movie-platforms");
        platformsContainer.innerHTML = ""; // Limpiamos por si acaso

        if (platformsData.length > 0) {
            platformsData.forEach(platform => {
                const platformElement = document.createElement("span");
                platformElement.innerText = platform.name;
                platformElement.classList.add("genre-tag");
                platformsContainer.appendChild(platformElement);
            });
        } else {
            platformsContainer.innerText = "No platforms available.";
        }

        const reviewsResponse = await fetch(`http://127.0.0.1:8085/movies/reviews/${movieId}`);
        const reviewsData = await reviewsResponse.json();

        const reviewsContainer = document.getElementById("movie-reviews");
        reviewsContainer.innerHTML = ""; // Limpiamos por si acaso

        if (reviewsData.length > 0) {
            reviewsData.forEach(review => {
                const reviewElement = document.createElement("li");
                reviewElement.innerText = `${review.name_reviewer}: ${review.comment}`;
                reviewsContainer.appendChild(reviewElement);
            });
        } else {
            reviewsContainer.innerText = "No reviews available.";
        }
    });
}