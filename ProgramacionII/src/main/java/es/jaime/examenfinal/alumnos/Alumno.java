package es.jaime.examenfinal.alumnos;

public final class Alumno {
    private final String nombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final String dni;

    public Alumno(String nombre, String primerApellido, String segundoApellido, String DNI) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getDni() {
        return dni;
    }
}
