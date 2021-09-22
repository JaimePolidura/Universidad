package ProgramacionII.microndas.programas.grill;

import ProgramacionII.microndas.programas.ProgramaMicro;

public final class GrillCalentar extends ProgramaMicro implements IncluyeSelectorGrill {
    public void grillYCalentar (int tiempo, int potencia, int grill) {
        selectorGrill.setPosicion(grill);
        selectorPotencia.setPotencia(potencia);
        selectorTiempo.setTiempo(tiempo);

        System.out.println("Grill calentando...");
    }
}
