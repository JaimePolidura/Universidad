package es.jaime.cruzenralla;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public final class Ventana2 extends Frame {
    private Button b1, b2, b3, b4;
    private CardLayout cl;
    private Panel cardpanel;

    public Ventana2(){
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
        panelbotones.add(buttonA);

        Button buttonB = new Button("B");
        panelbotones.add(buttonB);

        Button buttonC = new Button("C");
        panelbotones.add(buttonC);

        Button buttonD = new Button("D");
        panelbotones.add(buttonD);

        Panel inicioPanel = new Panel();
        inicioPanel.setBackground (new Color(.85f,.85f ,.85f));
        inicioPanel.add(new Label("Ejemplo de CardLayout"));

        Panel tresEnRallaPanel = new Panel();
        tresEnRallaPanel.setLayout(new GridLayout(3, 3));
        List<Button> buttons = new ArrayList<>();
        OnButtonClickedEventListener eventListener = new OnButtonClickedEventListener();
        for (int i = 0; i < 9; i++) {
            Button button = new Button(" ");
            button.addActionListener(eventListener);

            tresEnRallaPanel.add(button);
        }

        cardpanel.add(inicioPanel, "inicial");
        cardpanel.add(panelbotones, "Botones");
        cardpanel.add(panelAread, "Area");
        cardpanel.add(panelTexto, "Texto");
        cardpanel.add(tresEnRallaPanel, "3ER");

        Panel controlpanel = new Panel();
        b1 = new Button("Botones");
        b2 = new Button("Texto");
        b3 = new Button("Area");
        b4 = new Button("3ER");

        b1.addActionListener(e -> cl.show(cardpanel , "Botones"));
        b2.addActionListener(e -> cl.show(cardpanel , "Texto" ));
        b3.addActionListener(e -> cl.show(cardpanel, "Area"));
        b4.addActionListener(e -> cl.show(cardpanel, "3ER"));

        controlpanel.add(b1);
        controlpanel.add(b2);
        controlpanel.add(b3);
        controlpanel.add(b4);

        setLayout(new BorderLayout());
        add(controlpanel,BorderLayout.NORTH);
        add(cardpanel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Ventana2();
    }

    private final static class OnButtonClickedEventListener implements ActionListener {
        private boolean tocaX;

        private OnButtonClickedEventListener() {
            tocaX = false;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Button buttonClicked = (Button) e.getSource();
            String titleButton = buttonClicked.getLabel();

            if(!titleButton.equalsIgnoreCase(" ")){
                return;
            }

            if(tocaX){
                buttonClicked.setLabel("X");
            }else{
                buttonClicked.setLabel("O");
            }

            tocaX = !tocaX;
        }
    }
}
