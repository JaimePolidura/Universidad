package ProgramacionII.microndas.programas;

public final class CalentarProgramaMicro extends ProgramaMicro{
    public void calentar (int tiempo, int potencia) {
        selectorPotencia.setPotencia(potencia);
        selectorTiempo.setTiempo(tiempo);

        System.out.println("Calentando...");
    }
}
