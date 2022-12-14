package es.jaime.microndas.programas;

public final class DescongelarManual extends ProgramaMicro {

    public void descongelar (int tiempo) {
        selectorPotencia.setPotencia(100);
        selectorTiempo.setTiempo(tiempo);

        System.out.println("Descongelando...");
    }
}
