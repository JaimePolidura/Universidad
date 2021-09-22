package ProgramacionII.microndas;

import ProgramacionII.microndas.microndas.MxRoc;
import ProgramacionII.microndas.microndas.MxRoc1;
import ProgramacionII.microndas.microndas.MxRoc2;
import ProgramacionII.microndas.microndas.MxRoc3;

public class MicrondasTest {
    public static void main(String[] args) {
        MxRoc1 mxRoc1 = new MxRoc1();
        MxRoc2 mxRoc2 = new MxRoc2();
        MxRoc3 mxRoc3 = new MxRoc3();

        mxRoc1.calentarLechePrograma.calentarLeche();

        manipular(mxRoc1);
    }

    public static void manipular (MxRoc mxRoc) {
        mxRoc.calentarLechePrograma.calentarLeche();
        mxRoc.calentarPrograma.calentar(60, 900);
    }
}
