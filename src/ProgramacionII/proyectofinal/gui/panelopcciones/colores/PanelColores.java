package ProgramacionII.proyectofinal.gui.panelopcciones.colores;

import ProgramacionII.proyectofinal.Configuracion;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

public final class PanelColores extends Panel {
    private final List listaColores;
    private final Configuracion config;

    public PanelColores (Configuracion config) {
        this.config = config;

        super.setLayout(new FlowLayout());
        super.add(new Label("Colores"));

        this.listaColores = new List();
        listaColores.setMultipleMode(false);
        Arrays.stream(Colores.values()).forEach(color -> listaColores.add(color.nombre));
        listaColores.addItemListener(new OnItemSelected());

        super.add(listaColores);
    }

    private class OnItemSelected implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            List lista = (List) e.getSource();
            Color colorSeleccionado = Colores.getColorDeNombreDeLaLista(lista.getSelectedItem());

            config.setColor(colorSeleccionado);
        }
    }
}
