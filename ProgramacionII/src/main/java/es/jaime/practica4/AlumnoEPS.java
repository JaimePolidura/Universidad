package es.jaime.practica4;

public abstract class AlumnoEPS implements Alumno{
    protected final String nombre;
    protected final String apellidos;
    protected final float notaMedia;
    protected final boolean isBecario;

    public AlumnoEPS(String nombre, String apellidos, float notaMedia, boolean isBecario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.notaMedia = notaMedia;
        this.isBecario = isBecario;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public float getNotaMedia() {
        return notaMedia;
    }

    @Override
    public abstract void isBecario();
}
