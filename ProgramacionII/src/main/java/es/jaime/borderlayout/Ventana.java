package es.jaime.borderlayout;

import javax.swing.*;
import java.awt.*;

public final class Ventana extends JFrame {
    public Ventana () {
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setSize(600, 450);
        super.setLocation(400, 200);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");

        super.setLayout(new BorderLayout());
        super.add(button1, BorderLayout.NORTH);
        super.add(button2, BorderLayout.SOUTH);
        super.add(button3, BorderLayout.EAST);
        super.add(button4, BorderLayout.WEST);
        super.add(button5, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }

}
