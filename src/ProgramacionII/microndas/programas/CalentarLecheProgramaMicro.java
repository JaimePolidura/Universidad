package ProgramacionII.microndas.programas;

public final class CalentarLecheProgramaMicro extends ProgramaMicro{
    public void calentarLeche () {
        selectorPotencia.setPotencia(900);
        selectorTiempo.setTiempo(60);

        System.out.println("Calentando leche...");
    }
}
