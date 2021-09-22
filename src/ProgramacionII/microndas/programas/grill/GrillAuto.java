package ProgramacionII.microndas.programas.grill;

import ProgramacionII.microndas.programas.ProgramaMicro;

public final class GrillAuto extends ProgramaMicro implements IncluyeSelectorGrill {
    public void grillAuto (int tiempo, int grill) {
        selectorPotencia.setPotencia(0);
        selectorTiempo.setTiempo(tiempo);
        selectorGrill.setPosicion(grill);

        System.out.println("Grill auto...");
    }
}
