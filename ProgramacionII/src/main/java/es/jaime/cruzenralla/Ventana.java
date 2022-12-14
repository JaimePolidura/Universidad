package es.jaime.cruzenralla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public final class Ventana extends JFrame {
    private List<Button> buttons;

    public Ventana(){
        super.setSize(400, 400);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new GridLayout(3, 3));

        initilizaComponents();
    }

    private void initilizaComponents () {
        OnButtonClickedEventListener listener = new OnButtonClickedEventListener();
        this.buttons = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            Button newButton = new Button(" ");
            newButton.addActionListener(listener);

            super.add(newButton);
        }
    }

    private final static class OnButtonClickedEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button buttonClicked = (Button) e.getSource();
            String titleButton = buttonClicked.getLabel();

            if(titleButton.equalsIgnoreCase(" ")){
                buttonClicked.setLabel("X");
            }
        }
    }

    public static void main(String[] args) {
        new Ventana();
    }
}
