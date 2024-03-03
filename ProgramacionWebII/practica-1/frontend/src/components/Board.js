import 'bootstrap/dist/css/bootstrap.min.css';
import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

function Board() {
    const [elements, setElements] = useState([]);
    const navigate = useNavigate();

    const token = localStorage.getItem("token");

    if(token === undefined || token === ""){
        navigate("/login");
    }

    for(let role of JSON.parse(localStorage.getItem("roles"))) {
        axios.get("http://localhost:8080/api/test/" + role, {
            "x-access-token": token
        }).then(res => {
            setElements([...elements, res])
        }).catch(err => {
            console.log(err);
            navigate("/login");
        });
    }

    return (
        <div>
            <h1>Contenido</h1>
            <ul>
                {elements.map(element => {
                    <li>{element}</li>
                })}
            </ul>
        </div>
    );
}

export default Board;
