package ProgramacionII.alumnos;

public class Alumno {
    private String nombre;
    private String apellidos;
    private String dni;
    private float nota;

    public Alumno(String nombre, String apellidos, String dni, float nota) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.nota = nota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public float getNota() {
        return nota;
    }
}
