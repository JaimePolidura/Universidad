package ProgramacionII.lienzo;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class Dibujo extends Frame {
   // Para almacenar las diferentes figuras que se van mostrando
   private Vector figs = new Vector();

   private Panel panelOpccionesTexto;

   private String texto;

   // List para ofrecer diferentes figuras
   private List listaFiguras;

   // Checkbox para seleccionar figura rellena o no
   private Checkbox  relleno;

   // List para ofrecer diferentes colores
   private List listaColores;

   // Boton para deshacer (de forma indefinida)
   private Button deshacer;

   // HashMap para almacenar la informacion seleccionada por el usuario
   private HashMap informacion = new HashMap();

   // Lienzo es un Panel donde dibujar
   private Lienzo lienzo;
   

   // Clase Lienzo donde se mostraran las figuras
   class Lienzo extends Panel{
	   
	   public Lienzo(){
		   super();
	   }
      // Ocultamos paint(Graphics g) de la clase Panel
      public void paint(Graphics g){         
         // Si hay figuras en el Vector lo recorremos y las vamos pintando         
         if (figs.size()>0)
            for (int i=0; i<figs.size(); i++)
                  ((Figura)figs.get(i)).pintar(g);
      }      
      // Metodo que crea una nueva figura a partir de la informaci�n que 
      // contiene el HashMap y de las coordenadas que se pasan, 
      // almacena la figura en el Vector y repinta el Lienzo
      public void dibuja(int x1,int y1,int x2,int y2) {  
    	  Color color;
    	  String tf =(String)informacion.get("TipoFigura");
    	  String r = (String)informacion.get("Relleno");
    	  String c = (String)informacion.get("Color");

    	  if(tf!=null & r!=null & c!=null){
    		  if(c.equals("Rojo"))
    			  color=new Color(255,0,0);
    		  else
    			  if(c.equals("Verde"))
    				  color=new Color(0,255,0);
    			  else
    				  color=new Color(0,0,255);
    		  
    		  if(tf.equals("Rectangulo"))
    			  figs.add(new Rectangulo(x1,y1,x2,y2,color,r.equals("si")));
    		  else if (tf.equalsIgnoreCase("Elipse"))
    			  figs.add(new Elipse(x1,y1,x2,y2,color,r.equals("si")));
    		  else
    		      figs.add(new Texto(x1, y1, (String) informacion.get("texto"), color));
    	 }
    	 this.repaint();
      }
      
   }
   
   // Clase para tratar los eventos del tipo ItemEvent
   class TrataEventosItem implements ItemListener {
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() instanceof Checkbox){			
			informacion.remove("Relleno");
			if(e.getStateChange()== ItemEvent.SELECTED)
				informacion.put("Relleno","si");
			else
				informacion.put("Relleno","no");
		} else{
			List a =(List)e.getSource();
			String selec = a.getSelectedItem();
			if(a==listaColores){				
				informacion.remove("Color");
				informacion.put("Color", selec);				
			} else{
			    if(a.getSelectedItem().equalsIgnoreCase("texto")){
			        panelOpccionesTexto.setVisible(true);
                }else{
                    panelOpccionesTexto.setVisible(false);
                }
			}
		}
		
	}
	   
   }

   // Clase para tratar los eventos del tipo MouseEvent sobre el boton
   class TrataEventosRatonBotonBoton extends MouseAdapter {
  		public void mouseClicked(MouseEvent e){
  			if(figs.size() >0){
  	  			figs.remove(figs.size()-1);
  	  			lienzo.repaint();
  	  		}
  		}	 

   }
  
   // Clase para tratar los eventos del tipo MouseEvent sobre el Lienzo
   // Se deben implementar mousePressed y mouseReleased
   class TrataEventosRatonBotonLienzo extends MouseAdapter { 
      //Atributos
	   private int x1,y1,x2,y2;	   
	   public void mousePressed(MouseEvent e){
		   x1=e.getX();
		   y1=e.getY();		   
	   }
	   public void 	mouseReleased(MouseEvent e){
		   x2=e.getX();
		   y2=e.getY();
		   lienzo.dibuja(x1, y1, x2, y2);
	   }
   } 
   
   class TrataEventosCierre extends WindowAdapter{
       public void windowClosing(WindowEvent e){
           System.exit(0);
        }
   }
   // Constructor 
   public Dibujo() { 
      
      informacion.put("TipoFigura","Elipse");
      informacion.put("Color","Rojo");
      informacion.put("Relleno","no");

   //Creacion del lienzo y de los paneles con los componentes
      //Gestor de organizaci�n de la ventana
      setLayout(new BorderLayout());
      
      //Paneles
      Panel panelOpciones = new Panel();
      Panel panelFiguras = new Panel();
      Panel panelColores = new Panel();
      //
      lienzo = new Lienzo();
      
      //Boton
      deshacer = new Button("Deshacer");
      //Listas
      listaFiguras = new List();
      listaFiguras.add("Elipse");
      listaFiguras.add("Rectangulo");
      listaFiguras.add("Texto");

      this.panelOpccionesTexto = new Panel();
      TextField textField = new TextField(6);
       textField.addKeyListener(new OnTextBoxChanged());
       panelOpccionesTexto.add(textField);
       panelFiguras.setLayout(new GridLayout(2, 1));
       panelFiguras.add(panelOpccionesTexto);

      listaColores = new List();
      listaColores.setMultipleMode(false);
      listaColores.add("Rojo");
      listaColores.add("Verde");
      listaColores.add("Azul");
      
      //Checkbox
      relleno = new Checkbox("Relleno");
      
      //Gestor de organizaci�n de los paneles
      panelFiguras.setLayout(new FlowLayout());
      panelFiguras.add(new Label("Tipo Figura"));
      panelFiguras.add(listaFiguras);
      
      panelColores.setLayout(new FlowLayout());
      panelColores.add(new Label("Color"));
      panelColores.add(listaColores);
      
      panelOpciones.setLayout(new GridLayout(4,1));
      panelOpciones.add(panelFiguras);
      panelOpciones.add(relleno);
      panelOpciones.add(panelColores);
      panelOpciones.add(deshacer);

   //Registro de auditores de eventos
      TrataEventosItem tei = new TrataEventosItem();
      listaFiguras.addItemListener(tei);
      listaColores.addItemListener(tei);
      relleno.addItemListener(tei);
      deshacer.addMouseListener(new TrataEventosRatonBotonBoton());
      lienzo.addMouseListener(new TrataEventosRatonBotonLienzo());
      this.addWindowListener(new TrataEventosCierre());
      //A�adimos a la ventana el Lienzo y el panel de opciones 
      this.add(lienzo,BorderLayout.CENTER);
      this.add(panelOpciones,BorderLayout.EAST);
      setSize(800,500);      
      setVisible(true);      
   }

   private class OnTextBoxChanged extends KeyAdapter {
       @Override
       public void keyReleased(KeyEvent e) {
           TextField textField = (TextField) e.getSource();

           texto = textField.getText();

           informacion.put("texto", texto);
       }
   }

   public static void main(String[] args) {
      new Dibujo();
   }
}
