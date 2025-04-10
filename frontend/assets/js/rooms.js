function registerRoom(room) {
    return new Promise(async(resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "cinema_id": 0,
            "num_room": document.getElementById("num_room").value,
            "capacity": document.getElementById("capacity").value,
            "type": document.getElementById("type").value,
        });
        let response = await fetch("http://172.30.2.74:8085/rooms/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });
        let data = await response.text();
        console.log(data);
        getRooms();
    });
}

function getRooms(){
    return new Promise(async (resolve) => {
        var url = "http://172.30.2.74:8085/rooms/";
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
        var container = document.getElementById("listRooms");
        container.innerHTML = "";
        data.forEach(room => {
            console.log(room);
        });
    })
}