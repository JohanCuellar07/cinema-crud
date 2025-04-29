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
    });
}