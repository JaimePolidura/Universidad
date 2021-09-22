package ProgramacionII.proyectofinal.gui.panellienzo;

import ProgramacionII.proyectofinal.Configuracion;
import ProgramacionII.proyectofinal.AlmacenamientoFiguras;
import ProgramacionII.proyectofinal.figuras.Elipse;
import ProgramacionII.proyectofinal.figuras.Rectangulo;
import ProgramacionII.proyectofinal.figuras.Texto;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class PanelLienzo extends Panel {
    private final Configuracion config;
    private final AlmacenamientoFiguras figurasRecord;

    public PanelLienzo(Configuracion config) {
        this.config = config;
        this.figurasRecord = new AlmacenamientoFiguras();

        super.addMouseListener(new OnMouseClicked());
    }

    @Override
    public void paint(Graphics graphics){
        if(figurasRecord.isEmpty()){
            return;
        }

        figurasRecord.getFiguras()
                .forEach(figura -> figura.dibujar(graphics));
    }

    public void dibujar () {
        if(config.figuraNoSeleccionada()) {
            this.repaint();
            return;
        }

        if(config.getTipoFigura().equalsIgnoreCase("texto")){
            dibujarTexto();
        }else{
            dibujaFiguraGeometrica();
        }

        this.repaint();
    }

    public void dibujarTexto() {
        Color color = config.getColor();
        String nombreFigura = config.getTipoFigura();
        String texto = config.getTexto();
        int x1 = config.getX1();
        int y1 = config.getY1();
        Font font = config.getFuente();

        figurasRecord.addFigura(new Texto(texto, x1, y1, color, font));
    }

    public void dibujaFiguraGeometrica() {
        Color color = config.getColor();
        String nombreFigura = config.getTipoFigura();
        boolean relleno = config.getRelleno();
        int x1 = config.getX1();
        int y1 = config.getY1();
        int x2 = config.getX2();
        int y2 = config.getY2();

        switch (nombreFigura.toLowerCase()) {
            case "rectangulo":
                figurasRecord.addFigura(new Rectangulo(x1, y1, x2, y2, color, relleno));
                break;
            case "elipse":
                figurasRecord.addFigura(new Elipse(x1, y1, x2, y2, color, relleno));
                break;
        }
    }

    public void deshacer () {
        figurasRecord.deshacer();
        super.repaint();
    }

    public void rehacer () {
        figurasRecord.rehacer();
        super.repaint();
    }

    private class OnMouseClicked extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x1 = e.getX();
            int y1 = e.getY();

            config.setPunto1(x1, y1);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x2 = e.getX();
            int y2 = e.getY();

            config.setPunto2(x2, y2);

            dibujar();
        }
    }
}
