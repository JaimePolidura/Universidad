package es.jaime.practica4.tarea1;

import es.jaime.practica4.AlumnoEPS;

public class AlumnoGII extends AlumnoEPS {
    public AlumnoGII(String nombre, String apellidos, float notaMedia, boolean isBecario) {
        super(nombre, apellidos, notaMedia, isBecario);
    }

    @Override
    public void isBecario() {
        System.out.println(isBecario);
    }
}
