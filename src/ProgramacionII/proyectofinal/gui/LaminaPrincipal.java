package ProgramacionII.proyectofinal.gui;

import ProgramacionII.proyectofinal.Configuracion;
import ProgramacionII.proyectofinal.gui.panellienzo.PanelLienzo;
import ProgramacionII.proyectofinal.gui.panelopcciones.colores.PanelColores;
import ProgramacionII.proyectofinal.gui.panelopcciones.deshacerrehacer.PanelDeshacerRehacer;
import ProgramacionII.proyectofinal.gui.panelopcciones.figuras.PanelFiguras;
import ProgramacionII.proyectofinal.gui.panelopcciones.relleno.PanelRelleno;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class LaminaPrincipal extends Frame {
    private final Configuracion config;
    private final Panel panelOpciones;
    private final PanelLienzo panelLienzo;

    public LaminaPrincipal () {
        super("Paint v2");
        super.setSize(400, 200);
        super.setExtendedState(Frame.MAXIMIZED_BOTH);
        super.addWindowListener(new OnWindowClosing());
        super.setLayout(new BorderLayout());

        this.config = new Configuracion();
        this.panelLienzo = new PanelLienzo(config);
        this.panelOpciones = new Panel();

        panelOpciones.setLayout(new GridLayout(4,1));
        GridLayout gridLayout = new GridLayout();
        panelOpciones.add(new PanelFiguras(config));
        panelOpciones.add(new PanelRelleno(config));
        panelOpciones.add(new PanelColores(config));
        panelOpciones.add(new PanelDeshacerRehacer(panelLienzo));

        super.add(panelOpciones, BorderLayout.EAST);
        super.add(panelLienzo, BorderLayout.CENTER);
    }

    private static class OnWindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(-1);
        }
    }
}
