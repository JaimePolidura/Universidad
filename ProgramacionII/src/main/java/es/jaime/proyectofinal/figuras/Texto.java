package es.jaime.proyectofinal.figuras;

import java.awt.*;

public final class Texto implements Figura{
    private final String texto;
    private final Color color;
    private final int x, y;
    private final Font fuente;

    public Texto(String texto, int x, int y, Color color, Font fuente) {
        this.texto = texto;
        this.x = x;
        this.y = y;
        this.color = color;
        this.fuente = fuente;
    }

    @Override
    public void dibujar(Graphics graphics) {
        if(texto == null || texto.equalsIgnoreCase("")){
            return;
        }

        graphics.setColor(color);
        graphics.setFont(fuente);
        graphics.drawString(texto, x, y);

        graphics.drawString(texto, x, y);
    }
}
