package ejemplolisok;

public final class GuardadorBloqueMySQL implements GuardadorBloque {
    @Override
    public void guardar(Bloque bloque) {
        //Llamar a la base de datos
        System.out.println("Gurdado en mysql");
    }
}
