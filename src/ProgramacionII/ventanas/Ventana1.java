package ProgramacionII.ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Ventana1 extends JFrame {
    private final JButton button;

    public Ventana1() {
        super.setVisible(true);
        super.setSize(600, 450);
        super.setLocation(400, 200);
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.button = new JButton("Boton sin color");

        super.add(this.button);
        this.button.addActionListener(new onButtonClicked());
    }

    public static void main(String[] args) {
        Ventana1 ventana = new Ventana1();
    }

    private static class onButtonClicked implements ActionListener {
        private boolean changeToRed;

        public onButtonClicked () {
            this.changeToRed = true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if(changeToRed) {
                clickedButton.setBackground(Color.RED);
                clickedButton.setText("Boton rojo");
            }else{
                clickedButton.setBackground(Color.GREEN);
                clickedButton.setText("Boton verde");
            }

            changeToRed = !changeToRed; //Swap
        }
    }
}

