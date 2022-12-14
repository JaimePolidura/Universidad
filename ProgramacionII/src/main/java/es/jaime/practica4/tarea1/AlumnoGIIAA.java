package es.jaime.practica4.tarea1;

import es.jaime.practica4.AlumnoEPS;

public class AlumnoGIIAA extends AlumnoEPS {
    public AlumnoGIIAA(String nombre, String apellidos, float notaMedia, boolean isBecario) {
        super(nombre, apellidos, notaMedia, isBecario);
    }

    @Override
    public void isBecario() {
        System.out.println(isBecario);
    }
}
