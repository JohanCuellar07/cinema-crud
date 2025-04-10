function registerUser(user) {
    return new Promise(async(resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }
        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("name").value,
            "last_name": document.getElementById("last_name").value,
            "phone": document.getElementById("phone").value,
            "email": document.getElementById("email").value
        });
        let response = await fetch("http://172.30.2.74:8085/users/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });
        let data = await response.text();
        console.log(data);
        getUsers();
    });
}

function getUsers(){
    return new Promise(async (resolve) => {
        var url = "http://172.30.2.74:8085/users/";
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
        var container = document.getElementById("listUsers");
        container.innerHTML = "";
        data.forEach(user => {
            console.log(user);
        });
    })
}