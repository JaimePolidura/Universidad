package ProgramacionII.proyectofinal.gui.panelopcciones.deshacerrehacer;

import ProgramacionII.proyectofinal.gui.panellienzo.PanelLienzo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PanelDeshacerRehacer extends Panel {
    private final PanelLienzo panelLienzo;

    private final Button buttonDeshacer;
    private final Button buttonRehacer;

    public PanelDeshacerRehacer(PanelLienzo panelLienzo) {
        this.panelLienzo = panelLienzo;
        this.buttonDeshacer = new Button("Deshacer");
        buttonDeshacer.addActionListener(new OnBotonDeshacerPulsado());

        this.buttonRehacer = new Button("Rehacer");
        buttonRehacer.addActionListener(new OnBotonRehacerPulsado());

        super.setLayout(new GridLayout(2, 1));

        super.add(buttonDeshacer);
        super.add(buttonRehacer);
    }

    private class OnBotonDeshacerPulsado implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelLienzo.deshacer();
        }
    }

    private class OnBotonRehacerPulsado implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelLienzo.rehacer();
        }
    }
}
