package ejemplolisok;

public final class Main {
    public static void main(String[] args) {
        Bloque bloque = new Bloque();

        GuardadorBloque guardadorBloque = new GuardadorBloqueMySQL();
        guardadorBloque.guardar(bloque);

        guardadorBloque = new GuardadorBloqueArchivoBinario();
        guardadorBloque.guardar(bloque);
    }
}
