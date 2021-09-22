package ProgramacionII.microndas.microndas;

import ProgramacionII.microndas.programas.CalentarLecheProgramaMicro;
import ProgramacionII.microndas.programas.CalentarProgramaMicro;

public class MxRoc {
    public final CalentarProgramaMicro calentarPrograma;
    public final CalentarLecheProgramaMicro calentarLechePrograma;

    public MxRoc() {
        this.calentarPrograma = new CalentarProgramaMicro();
        this.calentarLechePrograma = new CalentarLecheProgramaMicro();
    }
}
