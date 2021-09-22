package ProgramacionII.examenfinal.alumnos;



import java.util.ArrayList;
import java.util.List;

public final class GestionAlumnos {
    private final List<Alumno> alumnos;

    public GestionAlumnos() {
        this.alumnos = new ArrayList<>();
    }

    public boolean dniRegistrado (String dni) {
        return alumnos.stream()
                .anyMatch(alumno -> alumno.getDni().equalsIgnoreCase(dni));

        /* Lo equivalente seria esto:
        boolean registrado = false;

        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {
                registrado = true;
                break;
            }
        }

        return registrado;*/
    }

    public void add (Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}
