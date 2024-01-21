document.getElementById("register").addEventListener("click", e => {
    fetch("http://localhost:8080/api/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("nombre").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => {
        if(!res.ok) {
            throw new Error(res.body);
        } else {
            return res.json();
        }
    })
    .then(res => {
        window.location.href = "http://localhost:8080/login.html";
    })
    .catch(err => {
        window.alert(err);
    })
});