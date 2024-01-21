document.getElementById("login").addEventListener("click", e => {
    
    fetch("http://localhost:8080/api/auth/signin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("usuario").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => {
        if(!res.ok){
            throw Error("Error al iniciar sesion");
        }else{
            return res.json();
        }
    })
    .then(res => {
        window.location.href = "http://localhost:8080/board.html";
        localStorage.setItem("token", res.accessToken);
        localStorage.setItem("roles", JSON.stringify(res.roles));
    })
    .catch(err => {
        window.alert("Error al inciar sesion");
        document.getElementById("usuario").value = "";
        document.getElementById("password").value = "";
    });
});