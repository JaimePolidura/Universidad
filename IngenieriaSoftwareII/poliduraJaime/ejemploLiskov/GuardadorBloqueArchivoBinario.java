package ejemplolisok;

public final class GuardadorBloqueArchivoBinario implements GuardadorBloque {
    @Override
    public void guardar(Bloque bloque) {
        //Gurdar bloque en un archivo en binario
        System.out.println("Gurdado en un archivo");
    }
}
