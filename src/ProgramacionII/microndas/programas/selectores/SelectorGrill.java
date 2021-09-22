package ProgramacionII.microndas.programas.selectores;


public final class SelectorGrill {
    private int posicion;

    public SelectorGrill () {
        this.posicion = 0;
    }

    public void setPosicion (int nuevaPosicion) {
        if (posicion < 0 || posicion > 3) {
            System.err.println("Tipo incorrecto");
            return;
        }

        this.posicion = nuevaPosicion;
    }

    public int getPosicion() {
        return posicion;
    }
}
