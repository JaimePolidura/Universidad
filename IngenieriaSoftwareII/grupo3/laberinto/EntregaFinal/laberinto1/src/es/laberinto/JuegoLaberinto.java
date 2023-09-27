package es.laberinto;

import es.laberinto.bloques.Bloque;
import es.laberinto.entides.Entidad;
import es.laberinto.utils.Direccion;

import java.util.List;
import java.util.Scanner;

public final class JuegoLaberinto {
    private final RenderizadorMundo renderizadorMundo;
    private final Scanner scanner;
    private final Mundo mundo;

    public JuegoLaberinto(int ancho, int largo) {
        this.renderizadorMundo = new RenderizadorMundo();
        this.scanner = new Scanner(System.in);
        this.mundo = new Mundo(ancho, largo);
    }

    public void insertarBloques(Bloque[][] bloques) {
        this.mundo.insertarBloques(bloques);
    }

    public void insertarEntidades(List<Entidad> entidades) {
        this.mundo.insertarEntidades(entidades);
    }

    public void iniciar() {
        while(true){
            leerInputUsuarioYAplicar();
            mundo.tick();
            renderizadorMundo.renderizar(this.mundo);
        }
    }

    private void leerInputUsuarioYAplicar() {
        switch (this.scanner.next()) {
            case "w" -> mundo.moverPersonaje(Direccion.ARRIBA);
            case "a" -> mundo.moverPersonaje(Direccion.IZQUIERDA);
            case "d" -> mundo.moverPersonaje(Direccion.DERECHA);
            case "s" -> mundo.moverPersonaje(Direccion.ABAJO);
            case "r" -> mundo.desmontarse();
            case "e" -> System.exit(1);
        }
    }

    public Mundo getMundo() {
        return this.mundo;
    }
}
