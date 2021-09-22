package ProgramacionII.examenfinal.asignatura;

import java.util.ArrayList;
import java.util.List;

public final class GestionAsignaturas {
    private final List<Asignatura> asignaturas;

    public GestionAsignaturas() {
        this.asignaturas = new ArrayList<>();
    }

    public void add (Asignatura asignatura) {
        this.asignaturas.add(asignatura);
    }

    public List<Asignatura> getAsignaturas () {
        return asignaturas;
    }
}
