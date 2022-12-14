package es.jaime.examenfinal.gui.tabs;

import es.jaime.examenfinal.alumnos.Alumno;
import es.jaime.examenfinal.alumnos.GestionAlumnos;
import es.jaime.examenfinal.asignatura.Asignatura;
import es.jaime.examenfinal.asignatura.GestionAsignaturas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class MostrarInformacionTab extends Panel {
    private final GestionAlumnos gestionAlumnos;
    private final GestionAsignaturas gestionAsignaturas;

    private final TextArea textAreaInfo;
    private final Panel panelButtons;
    private final Button botonMostrarAlumnos;
    private final Button botonMostrarAsignaturas;

    public MostrarInformacionTab(GestionAlumnos gestionAlumnos, GestionAsignaturas gestionAsignaturas) {
        this.gestionAlumnos = gestionAlumnos;
        this.gestionAsignaturas = gestionAsignaturas;

        super.setLayout(new BorderLayout());

        this.textAreaInfo = new TextArea();
        textAreaInfo.setEditable(false);

        this.panelButtons = new Panel();
        this.botonMostrarAsignaturas = new Button("Mostrar asignaturas");
        botonMostrarAsignaturas.addActionListener(new OnBotonClickeadoMostrarAsignaturas());

        this.botonMostrarAlumnos = new Button("Mostrar alumnos");
        botonMostrarAlumnos.addActionListener(new OnBotonClickeadoMostrarAlumnos());
        panelButtons.add(botonMostrarAlumnos);
        panelButtons.add(botonMostrarAsignaturas);

        add(textAreaInfo, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private class OnBotonClickeadoMostrarAsignaturas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Asignatura> asignaturas = gestionAsignaturas.getAsignaturas();
            textAreaInfo.setText("");

            for (Asignatura asignatura : asignaturas) {
                textAreaInfo.append("----------------" + "\n");
                textAreaInfo.append("Nombre asignatura: " + asignatura.getNombre() + "\n");
                textAreaInfo.append("Grado: " + asignatura.getGrado() + "\n");
                textAreaInfo.append("Numero de alumnos: " + asignatura.getNumeroAlumnos() + "\n");
                textAreaInfo.append("----------------" + "\n");
            }
        }
    }

    private class OnBotonClickeadoMostrarAlumnos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Alumno> alumnos = gestionAlumnos.getAlumnos();
            textAreaInfo.setText("");

            for (Alumno alumno : alumnos) {
                textAreaInfo.append("Nombre: " + alumno.getNombre() + "\n");
                textAreaInfo.append("Primer apellido: " + alumno.getPrimerApellido() + "\n");
                textAreaInfo.append("Segundo apellido: " + alumno.getSegundoApellido() + "\n");
                textAreaInfo.append("DNI: " + alumno.getDni() + "\n");
                textAreaInfo.append("----------------" + "\n");
            }
        }
    }
}
