package es.jaime.proyectofinal.figuras;

import java.awt.*;

public final class Rectangulo implements Figura {
	private final Color color;
	private final boolean relleno;
	private int x1,y1,x2,y2;
	private int anchura, altura;

	public Rectangulo(int x1, int y1, int x2, int y2, Color color, boolean relleno){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
		this.relleno = relleno;

		procesaPuntos();		
	}
	
	private void procesaPuntos(){
		if(x1<x2 & y1<y2){
			anchura = x2-x1;
			altura = y2-y1;
		}else{
			if(x1<x2 & y1>y2){
				anchura = x2 - x1;
				altura = y1-y2;
				this.y1 = y1-altura;
			}else if(x1>x2 & y1<y2){
				anchura =x1-x2;
				altura = y2-y1;
				this.x1=x1-anchura;
			}else{
				anchura=x1-x2;
				altura=y1-y2;
				this.x1=x1-anchura;
				this.y1=y1-altura;
			}
		}
	}

	@Override
	public void dibujar(Graphics graphics){
		graphics.setColor(color);

		if(!relleno) {
			graphics.drawRect(x1, y1, anchura, altura);
		}else {
			System.out.println(x1 + " " + y1 + " " + anchura + " " + altura + " " + color);
			graphics.fillRect(x1, y1, anchura, altura);
		}
	}
}
