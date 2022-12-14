package es.jaime.microndas.microndas;

import es.jaime.microndas.programas.CalentarLecheProgramaMicro;
import es.jaime.microndas.programas.CalentarProgramaMicro;

public class MxRoc {
    public final CalentarProgramaMicro calentarPrograma;
    public final CalentarLecheProgramaMicro calentarLechePrograma;

    public MxRoc() {
        this.calentarPrograma = new CalentarProgramaMicro();
        this.calentarLechePrograma = new CalentarLecheProgramaMicro();
    }
}
