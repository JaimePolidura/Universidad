package es.jaime.proyectofinal.gui.panelopcciones.figuras;

import es.jaime.proyectofinal.Configuracion;
import es.jaime.proyectofinal.figuras.TipoFiguras;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public final class PanelFiguras extends Panel {
    private static final java.util.List<String> fuentes = Arrays.asList("TimesRoman", "SansSerif");
    private static final java.util.List<Integer> tamanos = Arrays.asList(10, 12, 14, 16, 18, 20, 22, 24, 27);

    private final Configuracion config;

    private List listaFiguras;
    private Panel introducirTextoPanel;
    private Panel introducirFigurasPanel;

    public PanelFiguras(Configuracion config) {
        this.config = config;
        super.setLayout(new GridLayout(2, 1));

        setUpPanelSeleccionFiguras();
        setUpComponentesPanelOpccionesTexto();
    }

    private void setUpPanelSeleccionFiguras () {
        this.introducirFigurasPanel = new Panel();
        introducirFigurasPanel.setLayout(new FlowLayout());

        introducirFigurasPanel.add(new Label("Introdce figura"));
        this.listaFiguras = new List();
        listaFiguras.setMultipleMode(false);
        for(TipoFiguras figuraEnEnum : TipoFiguras.values()){
            listaFiguras.add(figuraEnEnum.nombre);
        }
        listaFiguras.addItemListener(new OnFiguraSeleccionada());
        introducirFigurasPanel.add(listaFiguras);

        super.add(introducirFigurasPanel);
    }

    private void setUpComponentesPanelOpccionesTexto () {
        this.introducirTextoPanel = new Panel();
        introducirTextoPanel.setLayout(new BorderLayout());

        TextField textField = new TextField(5);
        textField.addKeyListener(new OnTextboxChanged());
        introducirTextoPanel.add(textField, BorderLayout.NORTH);

        List tamano = new List();
        tamanos.forEach(t -> tamano.add(String.valueOf(t)));
        tamano.addItemListener(new OnTamanoSeleccionado());
        introducirTextoPanel.add(tamano, BorderLayout.CENTER);
        List fuentesLista = new List();
        fuentes.forEach(fuentesLista::add);
        fuentesLista.addItemListener(new OnFuenteSeleccionado());
        introducirTextoPanel.add(fuentesLista, BorderLayout.SOUTH);
        introducirTextoPanel.setVisible(false);

        super.add(introducirTextoPanel);
    }

    private class OnFuenteSeleccionado implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            String fuente = ((List) e.getSource()).getSelectedItem();

            List a = (List) e.getSource();

            if(a.getSelectedItem().equalsIgnoreCase("texto")){

            }

            int tamano = config.getFuente().getSize();

            config.setFuente(new Font(fuente, Font.PLAIN, tamano));
        }
    }

    private class OnTamanoSeleccionado implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int tamano = Integer.parseInt(((List) e.getSource()).getSelectedItem());

            String fontName = config.getFuente().getFontName();

            config.setFuente(new Font(fontName, Font.PLAIN, tamano));
        }
    }

    private class OnFiguraSeleccionada implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            String tipoFigura = ((List) e.getSource()).getSelectedItem();

            config.setTipoFigura(tipoFigura);

            introducirTextoPanel.setVisible(tipoFigura.equalsIgnoreCase("texto"));
        }
    }

    private class OnTextboxChanged extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            TextField textField = (TextField) e.getSource();

            config.setTexto(textField.getText());
        }
    }
}
