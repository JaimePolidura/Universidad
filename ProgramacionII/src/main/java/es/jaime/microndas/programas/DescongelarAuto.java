package es.jaime.microndas.programas;

public final class DescongelarAuto extends ProgramaMicro {
    public void descongelarAuto () {
        selectorTiempo.setTiempo(180);
        selectorPotencia.setPotencia(100);

        System.out.println("Descongelando...");
    }
}
