package ProgramacionII.proyectofinal.gui.panelopcciones.relleno;

import ProgramacionII.proyectofinal.Configuracion;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public final class PanelRelleno extends Panel {
    private final Checkbox checkboxRelleno;
    private final Configuracion config;

    public PanelRelleno(Configuracion config) {
        this.config = config;
        this.checkboxRelleno = new Checkbox("Relleno");
        checkboxRelleno.addItemListener(new OnRellenoChecked());

        super.add(checkboxRelleno);
    }

    private class OnRellenoChecked implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            config.setRelleno(e.getStateChange() == ItemEvent.SELECTED);
        }
    }
}
