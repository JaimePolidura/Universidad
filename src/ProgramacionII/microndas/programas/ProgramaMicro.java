package ProgramacionII.microndas.programas;

import ProgramacionII.microndas.programas.selectores.SelectorPotencia;
import ProgramacionII.microndas.programas.selectores.SelectorTiempo;

public class ProgramaMicro {
    protected final SelectorTiempo selectorTiempo;
    protected final SelectorPotencia selectorPotencia;
    
    public ProgramaMicro() {
        this.selectorTiempo = new SelectorTiempo();
        this.selectorPotencia = new SelectorPotencia();
    }
}
