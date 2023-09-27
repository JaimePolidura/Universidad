package es.laberinto;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.bloques.*;
import es.laberinto.modelo.entides.*;
import es.laberinto.modelo.Posicion;
import es.laberinto.vista.RenderizadorMundo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Main {
    public static void main(String[] args) {
        Simulador.simular(3,2, 2,3);
    }


}
