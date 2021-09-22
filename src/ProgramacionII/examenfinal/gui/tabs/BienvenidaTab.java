package ProgramacionII.examenfinal.gui.tabs;

import java.awt.*;

public final class BienvenidaTab extends Panel {
    private final Label labelBienvenida;

    public BienvenidaTab() {
        this.labelBienvenida = new Label("Bienvenido al examen final de programacion");

        super.setBackground(Color.LIGHT_GRAY);
        super.add(labelBienvenida);
    }
}
