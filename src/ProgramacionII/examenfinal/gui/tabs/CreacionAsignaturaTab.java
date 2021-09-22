package ProgramacionII.examenfinal.gui.tabs;

import ProgramacionII.examenfinal.asignatura.Asignatura;
import ProgramacionII.examenfinal.asignatura.GestionAsignaturas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class CreacionAsignaturaTab extends Panel {
    private final GestionAsignaturas gestionAsignaturas;

    private Label nombreLabel;
    private TextField nombreTextfield;
    private Label gradoLabel;
    private TextField gradoTextfield;
    private Label numeroAlumnosLabel;
    private TextField numeroAlumnosTextfield;

    private Button botonCrearAsignatura;
    private Label labelResultado;

    public CreacionAsignaturaTab(GestionAsignaturas gestionAsignaturas) {
        this.gestionAsignaturas = gestionAsignaturas;

        super.setLayout(new FlowLayout());
        setUpComponents();
    }

    private void setUpComponents() {
        this.nombreLabel = new Label("Nombre: ");
        this.nombreTextfield = new TextField(50);
        add(nombreLabel);
        add(nombreTextfield);

        this.gradoLabel = new Label("Grado: ");
        this.gradoTextfield = new TextField(45);
        add(gradoLabel);
        add(gradoTextfield);

        this.numeroAlumnosLabel = new Label("Numero alumnos: ");
        this.numeroAlumnosTextfield = new TextField(45);
        add(numeroAlumnosLabel);
        add(numeroAlumnosTextfield);


        this.botonCrearAsignatura = new Button("Crear asignautra");
        this.botonCrearAsignatura.addActionListener(new OnCrearAsignaturaBotonClickeado());
        add(botonCrearAsignatura);

        this.labelResultado = new Label();
        add(labelResultado);
    }

    private class OnCrearAsignaturaBotonClickeado implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = nombreTextfield.getText().trim();
            String grado = gradoTextfield.getText().trim();
            String numeroAlumnos = numeroAlumnosTextfield.getText().trim();

            labelResultado.setText("");

            if(nombre.equals("") || grado.equals("") || numeroAlumnos.equals("")){
                labelResultado.setText("Debes de completar todos los campos para poder crear una asignatura");
                return;
            }

            gestionAsignaturas.add(new Asignatura(nombre, grado, Integer.parseInt(numeroAlumnos)));
            labelResultado.setText("La asignatura " + nombre + " del grado: " + grado + " se ha creado correctamente");
            resetFormulario();
        }
    }

    private void resetFormulario() {
        nombreTextfield.setText("");
        gradoTextfield.setText("");
        numeroAlumnosTextfield.setText("");
    }
}
