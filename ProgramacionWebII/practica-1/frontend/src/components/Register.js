import 'bootstrap/dist/css/bootstrap.min.css';
import "../App.css";
import {useState} from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Register() {
    const [nombreUsuarioInput, setNombreUsuarioInput] = useState('');
    const [passwordInput, setPasswordInput] = useState('');
    const [emailInput, setEmailInput] = useState('');
    const navigate = useNavigate();
1
    const register = () => {
        axios.post("http://localhost:8080/api/auth/signup", {
            username: nombreUsuarioInput,
            email: emailInput,
            password: passwordInput,
            roles: ["USER", "ADMIN"]
        }).then(res => {
            navigate("/login");
        }).catch(err => {
            window.alert("Error al registrarse: " + err);
        });
    };

    return (
        <div className="dif-container centered-mycontainer">
            <h1 style={{textAlign: 'center'}}>Registrarse</h1>
            <input className="form-control"
                   onChange={(event) => setNombreUsuarioInput(event.target.value)}
                   id="nombre"
                   type="text"
                   placeholder="Tu nombre de usuario" /><br />
            <input className="form-control"
                   onChange={(event) => setEmailInput(event.target.value)}
                   id="email"
                   type="text"
                   placeholder="Email" /><br />
            <input className="form-control"
                   onChange={(event) => setPasswordInput(event.target.value)}
                   id="password"
                   type="password"
                   placeholder="Contraseña" /><br />
            <hr />
            <button id="register" onClick={register} className="btn btn-primary">Registrarse</button>
            <br />
            ¿Ya tienes cuenta? Haz click <a href="/login">aqui</a>
        </div>
    );
}

export default Register;
