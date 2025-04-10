function registerReview() {
    return new Promise (async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "movie_id": 0,
            "user_id": 0,
            "review": document.getElementById("review").value
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