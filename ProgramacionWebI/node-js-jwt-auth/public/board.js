const token = localStorage.getItem("token");
const roles = localStorage.getItem("roles");

for (let rol of JSON.parse(roles)) {
    fetch("http://localhost:8080/api/test/" + rol, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "x-access-token": token
        },
    })
    .then(res => {
        if(!res.ok){
            window.location.href = "http://localhost:8080/login.html";
        } else {
            return res.text();
        }
    })
    .then(res => {
        console.log(res);

        document.getElementById("contenido").innerHTML = res + "<br>";
    })
}