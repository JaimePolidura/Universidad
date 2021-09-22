package ProgramacionII.practica4.tarea1;

import ProgramacionII.practica4.AlumnoEPS;

public class AlumnoGII extends AlumnoEPS {
    public AlumnoGII(String nombre, String apellidos, float notaMedia, boolean isBecario) {
        super(nombre, apellidos, notaMedia, isBecario);
    }

    @Override
    public void isBecario() {
        System.out.println(isBecario);
    }
}
