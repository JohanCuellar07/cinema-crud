let index = 0;
function moveSlide(step) {
    const slider = document.getElementById("movieSlider");
    const cardWidth = document.querySelector(".movie-card").offsetWidth + 20; // Incluye márgenes
    const visibleMovies = Math.floor(document.querySelector(".slider-container").offsetWidth / cardWidth);
    const maxIndex = document.querySelectorAll(".movie-card").length - visibleMovies;
    index = Math.max(0, Math.min(index + step, maxIndex));
    slider.style.transform = `translateX(${-index * cardWidth}px)`;
}