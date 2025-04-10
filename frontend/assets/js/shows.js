function registerShow(show) {
    return new Promise(async(resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "movie_id": 0,
            "date": document.getElementById("date").value,
            "time": document.getElementById("time").value,
            "price": document.getElementById("price").value
        });
        let response = await fetch("http://172.30.2.74:8085/shows/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });
        let data = await response.text();
        console.log(data);
        getShows();
    });
}

function getShows(){
    return new Promise(async (resolve) => {
        var url = "http://172.30.2.74:8085/shows/";
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
        var container = document.getElementById("listShows");
        container.innerHTML = "";
        data.forEach(show => {
            console.log(show);
        });
    })
}