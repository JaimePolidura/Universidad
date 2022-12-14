package es.jaime.microndas.programas.grill;

import es.jaime.microndas.programas.ProgramaMicro;

public final class GrillCalentar extends ProgramaMicro implements IncluyeSelectorGrill {
    public void grillYCalentar (int tiempo, int potencia, int grill) {
        selectorGrill.setPosicion(grill);
        selectorPotencia.setPotencia(potencia);
        selectorTiempo.setTiempo(tiempo);

        System.out.println("Grill calentando...");
    }
}
