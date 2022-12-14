package es.jaime.examenfinal.gui;

import es.jaime.examenfinal.alumnos.GestionAlumnos;
import es.jaime.examenfinal.asignatura.GestionAsignaturas;
import es.jaime.examenfinal.gui.tabs.BienvenidaTab;
import es.jaime.examenfinal.gui.tabs.CreacionAlumnoTab;
import es.jaime.examenfinal.gui.tabs.CreacionAsignaturaTab;
import es.jaime.examenfinal.gui.tabs.MostrarInformacionTab;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Ventana extends Frame {
    private final Panel tabPanel;
    private final CardLayout tabLayout;
    private final Panel botonesControlPanel;

    public Ventana () {
        super("Ejemplo de ventana");
        super.setSize(500, 400);
        super.addWindowListener(new OnClosingWindowExit());
        super.setLayout(new BorderLayout());

        this.tabLayout = new CardLayout();
        this.tabPanel = new Panel();
        tabPanel.setLayout(tabLayout);

        this.botonesControlPanel = new Panel();

        setUpTabs();
        setUpBotones();

        add(botonesControlPanel, BorderLayout.SOUTH);
        add(tabPanel, BorderLayout.CENTER);

        tabLayout.show(tabPanel, "bienvenida");
    }

    private void setUpTabs() {
        GestionAlumnos gestionAlumnos = new GestionAlumnos();
        GestionAsignaturas gestionAsignaturas = new GestionAsignaturas();

        BienvenidaTab bienvenidaPanel = new BienvenidaTab();
        tabPanel.add(bienvenidaPanel, "bienvenida");

        CreacionAlumnoTab creacionAlumnoPanel = new CreacionAlumnoTab(gestionAlumnos);
        tabPanel.add(creacionAlumnoPanel, "alumno_crear");

        CreacionAsignaturaTab creacionAsignaturaPanel = new CreacionAsignaturaTab(gestionAsignaturas);
        tabPanel.add(creacionAsignaturaPanel, "asignatura_crear");

        MostrarInformacionTab mostrarInformacionPanel = new MostrarInformacionTab(gestionAlumnos, gestionAsignaturas);
        tabPanel.add(mostrarInformacionPanel, "mostrar");
    }

    private void setUpBotones() {
        Panel controlpanel = new Panel();
        Button crearAsignatura = new Button("Crear asignatura");
        Button crearAlumno = new Button("Crear alumnos");
        Button mostrarInformacion = new Button("Mostrar informacion");

        crearAsignatura.addActionListener(e -> tabLayout.show(tabPanel, "asignatura_crear"));
        crearAlumno.addActionListener(e -> tabLayout.show(tabPanel, "alumno_crear"));
        mostrarInformacion.addActionListener(e -> tabLayout.show(tabPanel, "mostrar"));

        botonesControlPanel.add(crearAsignatura);
        botonesControlPanel.add(crearAlumno);
        botonesControlPanel.add(mostrarInformacion);
    }

    private static class OnClosingWindowExit extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(1);
        }
    }
}
