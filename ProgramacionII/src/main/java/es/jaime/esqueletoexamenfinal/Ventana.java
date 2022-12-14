package es.jaime.esqueletoexamenfinal;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Ventana extends Frame{
	private Button b1, b2, b3, crearalumno, crearasignatura,mostraralumnos, mostrarasignaturas;
	private CardLayout cl;
	private Panel cardpanel;
	private TextField nombre, nombrea, grado, numalumnos, apellido1, apellido2, dni;
	private Label letreroalumno, letreroasignatura;
	private TextArea infor;
	

	
	private ArrayList alumnos;
	private ArrayList asignaturas;
	
	    Ventana(){
			//COMPLETAR: ESTABLECER LOS ATRIBUTOS
			
			//COMPLETAR: Panel que va a contener CUATRO paneles organizados como CardLayout

			
			//COMPLETAR: PANEL ASIGNATURAS
			Panel panelasignaturas = new Panel();

			//COMPLETAR: L�GICA BOT�N crearasignatura
			crearasignatura.addMouseListener(new MouseAdapter ( ){
				public void mousePressed(MouseEvent e ){

				}
			});
			
			//COMPLETAR: PANEL ALUMNOS
			Panel panelalumnos = new Panel();

			
			//COMPLETAR: L�GICA BOT�N crearalumno
			crearalumno.addMouseListener(new MouseAdapter ( ){
				public void mousePressed(MouseEvent e ){

				}
			});
			
			//COMPLETAR: PANEL INFORMACI�N
			Panel panelinformacion = new Panel();

			//COMPLETAR: L�GICA BOT�N mostraralumnos
			mostraralumnos.addMouseListener(new MouseAdapter ( ){
				
				public void mousePressed(MouseEvent e ){

				}
			});		
			//COMPLETAR: L�GICA BOT�N mostrarasignaturas
			mostrarasignaturas.addMouseListener(new MouseAdapter ( ){
				
				public void mousePressed(MouseEvent e ){

				}
				
			});	
			
			
			
			//Panel de inicio
			Panel inicioPanel = new Panel( ) ;
			inicioPanel.setBackground (new Color(.85f,.85f ,.85f));
			inicioPanel.add(new Label("Bienvenido al examen final de programaci�n" )) ;
			
			//A�adimos los tres panelens asignandoles un nombre
			cardpanel.add(inicioPanel, "inicio");
			cardpanel.add(panelasignaturas, "asignaturas");
			cardpanel.add(panelalumnos, "alumnos");
			cardpanel.add(panelinformacion, "informacion");
			
			//COMPLETAR Panel de control desde donde se selecciona el subpanel a mostrar
			Panel controlpanel = new Panel();

			
			//COMPLETAR L�GICA B1
			b1.addMouseListener(new MouseAdapter ( ){
				public void mousePressed(MouseEvent e ){
				
				}
			});
			
			//COMPLETAR L�GICA B2
			b2.addMouseListener(new MouseAdapter ( ){
				public void mousePressed(MouseEvent e ){
				
				}
			});
			
			//COMPLETAR L�GICA B3
			b3.addMouseListener(new MouseAdapter ( ){
				public void mousePressed(MouseEvent e ){
				
				}
			});
			
			controlpanel.add(b1);
			controlpanel.add(b2);
			controlpanel.add(b3);

			//COMPLETAR LAYOUT DEL FRAME
			

	}
}
