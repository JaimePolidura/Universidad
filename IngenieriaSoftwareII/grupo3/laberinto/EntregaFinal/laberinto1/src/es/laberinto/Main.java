package es.laberinto;

import es.laberinto.bloques.*;
import es.laberinto.entides.*;
import es.laberinto.utils.Posicion;

import java.util.List;
import java.util.Map;

public final class Main {
    private static final Posicion POSICION_BARCA = Posicion.crear(22, 8);
    private static final Posicion POSICION_ALFOMBRA = Posicion.crear(4, 8);
    private static final Posicion POSICION_CABALLO = Posicion.crear(13, 5);
    private static final Posicion POSICION_NPC = Posicion.crear(6, 13);
    private static final Posicion POSICION_PERSONAJE = Posicion.crear(3, 3);

    private static final int[][] MAPA_BLOQUES_ID = new int[][]{
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,8,8,8,0,0,0,0,0,0,0,8,8,8,0,0,3,3,3,3,3,3,3,1},
            {1,8,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,3,3,3,3,3,1},
            {1,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,5,3,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,5,1},
            {1,5,5,5,5,5,0,0,0,5,5,5,5,5,0,0,0,0,0,0,0,0,5,1},
            {1,5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,8,1},
            {1,5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,1},
            {1,5,0,1,1,1,0,0,0,1,1,1,0,5,8,8,8,8,8,8,8,8,5,1},
            {1,5,0,1,1,1,1,2,1,1,1,1,0,5,8,6,6,6,6,6,6,5,5,1},
            {1,5,0,0,1,0,0,0,0,0,1,0,0,5,6,6,6,6,6,5,5,5,5,1},
            {1,5,0,0,1,0,0,0,0,0,1,0,0,5,6,6,6,2,5,5,5,8,8,1},
            {1,5,0,0,1,0,0,0,0,0,1,0,0,5,6,6,5,2,5,6,6,6,8,1},
            {1,5,0,1,1,0,1,1,1,0,1,1,0,5,6,5,5,6,6,6,8,8,8,1},
            {1,5,0,1,1,0,0,0,0,0,1,1,0,5,5,5,6,6,6,6,6,8,1,1},
            {1,5,0,0,0,0,0,0,0,0,0,0,0,5,4,4,6,6,6,8,8,8,1,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,6,6,8,8,8,8,1,1},
            {1,7,7,4,4,4,4,4,4,4,4,4,4,4,4,4,6,6,8,8,8,8,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    private static final Map<Integer, Bloque> BLOQUES_BY_ID = Map.of(
            0, Suelo.crear(),
            1, Pared.crear(),
            2, AguaTurbulenta.crear(),
            3, Montana.crear(),
            4, CespedMedio.crear(),
            5, Agua.crear(),
            6, Arena.crear(),
            7, CespedAlto.crear(),
            8, CespedBajo.crear()
    );

    public static void main(String[] args) {
        JuegoLaberinto juegoLaberinto = new JuegoLaberinto(22, 26);
        juegoLaberinto.insertarBloques(crearArrayaBloques());
        juegoLaberinto.insertarEntidades(crearListaEntidades(juegoLaberinto.getMundo()));

        juegoLaberinto.iniciar();
    }

    private static List<Entidad> crearListaEntidades(Mundo mundo) {
        return List.of(
                Barca.crear(mundo, POSICION_BARCA),
                Alfombra.crear(mundo, POSICION_ALFOMBRA),
                Caballo.crear(mundo, POSICION_CABALLO),
                NPC.crear(mundo, POSICION_NPC),
                Personaje.crear(mundo, POSICION_PERSONAJE)
        );
    }

    private static Bloque[][] crearArrayaBloques() {
        Bloque[][] bloques = new Bloque[MAPA_BLOQUES_ID.length][MAPA_BLOQUES_ID[0].length];

        for (int i = 0; i < MAPA_BLOQUES_ID.length; i++) {
            for (int j = 0; j < MAPA_BLOQUES_ID[i].length; j++) {
                bloques[i][j] = BLOQUES_BY_ID.get(MAPA_BLOQUES_ID[i][j]);
            }
        }

        return bloques;
    }
}
