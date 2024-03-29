package es.jaime.microndas.microndas;

import es.jaime.microndas.programas.DescongelarAuto;
import es.jaime.microndas.programas.grill.GrillAuto;
import es.jaime.microndas.programas.grill.GrillCalentar;

public final class MxRoc3 extends MxRoc {
    public final DescongelarAuto descongelarAuto;
    public final GrillAuto grillAuto;
    public final GrillCalentar grillCalentar;

    public MxRoc3() {
        this.grillCalentar = new GrillCalentar();
        this.grillAuto = new GrillAuto();
        this.descongelarAuto = new DescongelarAuto();
    }
}
