package ProgramacionII.gestionUsuarios;

public final class Usuario {
    /**
     * Nombre del usuario.
     */
    private final String nombre;

    /**
     * Login/identificador del usuario.
     */
    private final String login;

    /**
     * Contraseña del usuario.
     */
    private final String password;


    /**
     * Crea un usuario a partir de su nombre, login y password.
     * @param nombre Nombre del usuario.
     * @param login Login del usuario.
     * @param password Contraseña del usuario.
     */
    public Usuario(String nombre, String login, String password) {
        this.nombre = nombre;
        this.login = login;
        this.password = password;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el login del usuario.
     *
     * @return Login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Obtiene la contraseña.
     * @return Contraseña.
     */
    public String getPassword() {
        return password;
    }
}
