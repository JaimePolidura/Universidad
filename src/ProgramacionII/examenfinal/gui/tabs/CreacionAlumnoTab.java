package ProgramacionII.examenfinal.gui.tabs;

import ProgramacionII.examenfinal.alumnos.GestionAlumnos;
import ProgramacionII.examenfinal.alumnos.Alumno;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class CreacionAlumnoTab extends Panel {
    private final GestionAlumnos gestionAlumnos;

    private Label nombreLabel;
    private TextField nombreTextfield;
    private Label primerApellidoLabel;
    private TextField primerApellidoTextfield;
    private Label segundoApellidoLabel;
    private TextField segundoApellidoTextfield;
    private Label dniLabel;
    private TextField dniTextfield;

    private Button buttonCrearAlumno;
    private Label labelResultado;

    public CreacionAlumnoTab(GestionAlumnos gestionAlumnos) {
        this.gestionAlumnos = gestionAlumnos;

        super.setLayout(new FlowLayout());
        setUpComponents();
    }

    private void setUpComponents() {
        this.nombreLabel = new Label("Nombre: ");
        this.nombreTextfield = new TextField(40);
        add(nombreLabel);
        add(nombreTextfield);

        this.primerApellidoLabel = new Label("Primer apellido: ");
        this.primerApellidoTextfield = new TextField(40);
        add(primerApellidoLabel);
        add(primerApellidoTextfield);

        this.segundoApellidoLabel = new Label("Segundo apellido: ");
        this.segundoApellidoTextfield = new TextField(45);
        add(segundoApellidoLabel);
        add(segundoApellidoTextfield);

        this.dniLabel = new Label("DNI: ");
        this.dniTextfield = new TextField(45);
        add(dniLabel);
        add(dniTextfield);

        this.buttonCrearAlumno = new Button("Crear alumno");
        this.buttonCrearAlumno.addActionListener(new OnCrearAlumnoBotonClickeado());
        add(buttonCrearAlumno);

        this.labelResultado = new Label();
        add(labelResultado);
    }

    private class OnCrearAlumnoBotonClickeado implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = nombreTextfield.getText().trim();
            String primerApellido = primerApellidoTextfield.getText().trim();
            String segundoApellido = segundoApellidoTextfield.getText().trim();
            String dni = dniTextfield.getText().trim();

            labelResultado.setText("");

            if(nombre.equals("") || primerApellido.equals("") || segundoApellido.equals("") || dni.equals("")){
                labelResultado.setText("Debes de completar todos los campos para poder crear un alumnos");
                return;
            }
            if(gestionAlumnos.dniRegistrado(dni)){
                labelResultado.setText("El alumno con DNI: " + dni + " ya esta registrado");
                return;
            }

            gestionAlumnos.add(new Alumno(nombre, primerApellido, segundoApellido, dni));

            labelResultado.setText("El alumno con DNI:" + dni + " se ha agregado correctamente");
            resetFormulario();
        }
    }

    private void resetFormulario() {
        nombreTextfield.setText("");
        primerApellidoTextfield.setText("");
        segundoApellidoTextfield.setText("");
        dniTextfield.setText("");
    }
}
