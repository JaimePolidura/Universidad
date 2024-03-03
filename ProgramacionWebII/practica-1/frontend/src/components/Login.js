import 'bootstrap/dist/css/bootstrap.min.css';
import "../App.css";
import {useState} from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Login() {
    const [nombreUsuarioInput, setNombreUsuarioInput] = useState('');
    const [passwordInput, setPasswordInput] = useState('');
    const navigate = useNavigate();

    const login = () => {
        axios.post("http://localhost:8080/api/auth/signin", {
            username: nombreUsuarioInput,
            password: passwordInput
        }).then(res => {
            localStorage.setItem("token", res.data.accessToken);
            localStorage.setItem("roles", JSON.stringify(res.data.roles));
            navigate('/board');
        }).catch(error => {
            window.alert("Error al inciar sesion");
            setNombreUsuarioInput("");
            setPasswordInput("");
        });
    };

    return (
        <div className="dif-container centered-mycontainer">
            <h1 style={{textAlign: 'center'}}>Login</h1>
            <input className="form-control"
                   onChange={(event) => setNombreUsuarioInput(event.target.value)}
                   id="usuario"
                   type="text"
                   placeholder="Tu nombre de usuario"/><br/>
            <input className="form-control"
                   onChange={(event) => setPasswordInput(event.target.value)}
                   id="password"
                   type="password"
                   placeholder="Contraseña"/><br/>
            <hr/>
            <button className="btn btn-primary" onClick={login} id="login">Login</button>
            <br />
            ¿No tienes cuenta? Haz click <a href="/register">aqui</a>
        </div>
    );
}

export default Login;
