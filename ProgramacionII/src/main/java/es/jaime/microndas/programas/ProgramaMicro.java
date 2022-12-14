package es.jaime.microndas.programas;

import es.jaime.microndas.programas.selectores.SelectorPotencia;
import es.jaime.microndas.programas.selectores.SelectorTiempo;

public class ProgramaMicro {
    protected final SelectorTiempo selectorTiempo;
    protected final SelectorPotencia selectorPotencia;
    
    public ProgramaMicro() {
        this.selectorTiempo = new SelectorTiempo();
        this.selectorPotencia = new SelectorPotencia();
    }
}
