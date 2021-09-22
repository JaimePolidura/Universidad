package ProgramacionII.cardlayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Ventana extends Frame {
    private Button b1, b2, b3;
    private CardLayout cl;
    private Panel cardpanel;

    public Ventana(){
        super("Ejemplo de ventana");
        cardpanel = new Panel();
        cl = new CardLayout();
        cardpanel.setLayout(cl);

        super.setVisible(true);
        super.setSize(400, 300);

        Panel panelTexto = new Panel();
        panelTexto.add(new TextField(10));
        panelTexto.add(new TextField(10));

        Panel panelAread = new Panel();
        TextArea textArea = new TextArea();
        panelAread.add(textArea);

        Panel panelbotones = new Panel();
        Button buttonA = new Button("A");
        OnButtonEventListeber listener = new OnButtonEventListeber(textArea);

        buttonA.addActionListener(listener);
        panelbotones.add(buttonA);

        Button buttonB = new Button("B");
        panelbotones.add(buttonB);
        buttonB.addActionListener(listener);

        Button buttonC = new Button("C");
        buttonC.addActionListener(listener);
        panelbotones.add(buttonC);

        Button buttonD = new Button("D");
        buttonD.addActionListener(listener);
        panelbotones.add(buttonD);

        Panel inicioPanel = new Panel( ) ;
        inicioPanel.setBackground (new Color(.85f,.85f ,.85f));
        inicioPanel.add(new Label("Ejemplo de CardLayout" )) ;

        cardpanel.add(inicioPanel, "inicial");
        cardpanel.add(panelbotones, "Botones");
        cardpanel.add(panelAread, "Area");
        cardpanel.add(panelTexto, "Texto");
        Panel controlpanel = new Panel();
        b1 = new Button("Botones");
        b2 = new Button("Texto");
        b3 = new Button("Area");

        b1.addActionListener(e -> cl.show(cardpanel , "Botones"));
        b2.addActionListener(e -> cl.show(cardpanel , "Texto" ));
        b3.addActionListener(e -> cl.show(cardpanel, "Area"));

        controlpanel.add(b1);
        controlpanel.add(b2);
        controlpanel.add(b3);
        setLayout(new BorderLayout());
        add(controlpanel,BorderLayout.NORTH);
        add(cardpanel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Ventana();
    }

    private static class OnButtonEventListeber implements ActionListener {
        private final TextArea textArea;

        public OnButtonEventListeber(TextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Button source = (Button) e.getSource();

            textArea.append(source.getLabel() + "\n");
        }
    }
}
