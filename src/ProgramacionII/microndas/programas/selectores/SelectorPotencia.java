package ProgramacionII.microndas.programas.selectores;


public final class SelectorPotencia {
    private int potencia;

    public SelectorPotencia() {
        this.potencia = 0;
    }

    public void incrementarPotencia () {
        if(this.potencia >= 900){
            System.err.println("No se puede superar 900 watts");
            return;
        }

        this.potencia = potencia + 100;
    }

    public void decrementarPotencia () {
        if(this.potencia < 0){
            System.err.println("No se puede bajar de los 0 watts");
            return;
        }

        this.potencia =- 100;
    }

    public void setPotencia(int potencia) {
        if(potencia < 0 || potencia > 900 || potencia % 100 != 0){
            System.err.println("Introduce un valor correcto");
            return;
        }

        this.potencia = potencia;
    }

    public int getPotencia() {
        return potencia;
    }
}
