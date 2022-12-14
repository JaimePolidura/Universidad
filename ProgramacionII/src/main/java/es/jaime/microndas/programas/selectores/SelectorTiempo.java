package es.jaime.microndas.programas.selectores;

public final class SelectorTiempo {
    private int tiempo;

    public SelectorTiempo() {
        this.tiempo = 0;
    }

    public void incrementarTiempo () {
        if(this.tiempo >= 1800){
            System.err.println("No se puede superar los 1800 segundos");

            return;
        }

        this.tiempo =+ 60;
    }

    public void decrementarTiempo () {
        if(this.tiempo < 0){
            System.err.println("No se puede bajar de los 0 segundos");

            return;
        }

        this.tiempo =+ 60;
    }

    public void setTiempo (int nuevoTiempo) {
        if (nuevoTiempo < 0 || nuevoTiempo > 1800 || nuevoTiempo % 60 != 0) {
            System.err.println("Introduce un valor correcto");
            return;
        }

        this.tiempo = nuevoTiempo;
    }

    public int getTiempo() {
        return tiempo;
    }
}
