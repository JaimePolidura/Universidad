package es.jaime.gestionUsuarios;

public final class Menu {
    /**
     * Opciones del menú.
     */
    private final String[] opciones;

    /**
     * Número total de opciones.
     */
    private final int nOpciones;

    /**
     * Primera opción libre.
     */
    private int opcionLibre;
    /**
     * Crea una menú de opciones con un número de opciones.
     * @param nO Número de opciones.
     */
    public Menu(int nO) {
        this.nOpciones = nO;
        this.opciones = new String[nO];
        this.opcionLibre = -1;
    }

    /**
     * Obtiene sólo las opciones introducidas en el menú.
     * @return Opciones del menú.
     */
    public String[] getOpciones() {
        return opciones;
    }

    /**
     * Devuelve el maximo numero de opcciones
     * que hay
     */
    public int getnOpciones() {
        return nOpciones;
    }

    /**
     * Añade una opción al menú.
     * @param op Opción del menú.
     */
    public void addOpcion(String op) {
        if(opcionLibre + 1 >= opciones.length){
           opciones[opcionLibre] = op;
           opcionLibre++;
        }
    }

}
