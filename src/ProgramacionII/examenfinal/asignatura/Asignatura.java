package ProgramacionII.examenfinal.asignatura;

public final class Asignatura {
    private final String nombre;
    private final String grado;
    private final int numeroAlumnos;

    public Asignatura(String nombre, String grado, int numeroAlumnos) {
        this.nombre = nombre;
        this.grado = grado;
        this.numeroAlumnos = numeroAlumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrado() {
        return grado;
    }

    public int getNumeroAlumnos() {
        return numeroAlumnos;
    }
}
