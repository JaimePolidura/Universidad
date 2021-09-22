package ProgramacionII.gestionUsuarios;


public final class SistemaGestionUsuarios {
    /**
     * Número máximo de usuarios.
     */
    private int nUsuarios;

    /**
     * Primera posición libre.
     */
    private int primerLibre;
    /**
     * Usuarios del sistema.
     */
    private Usuario[] usuarios;

    /**
     * Menús asociados a los usuarios.
     * El usuarios[i] tiene asociado el menus[i].
     */
    private Menu[] menus;

    /**
     * Crea un sistema de gestión de usuarios limitado en número.
     * @param nUsuarios Número total de usuarios.
     */
    public SistemaGestionUsuarios(int nUsuarios) {
        this.menus = new Menu[nUsuarios];
        this.usuarios = new Usuario[nUsuarios];
        this.primerLibre = -1;
        this.nUsuarios = nUsuarios;
    }

    /**
     * Añade un usuario al sistema y le asocia un menu de
     opciones.
     * @param usuario Usuario.
     * @param menu Menú de opciones.
     */
    public void addUsuario(Usuario usuario, Menu menu) {
        if((primerLibre + 1) <= usuarios.length){
            primerLibre++;
            usuarios[primerLibre] = usuario;
            menus[primerLibre] = menu;
        }
    }

    public Menu autentificaUsuario(String login, String password) {
        for(int i = 0; i < (primerLibre + 1); i++){
            if(usuarios[i].getLogin().equalsIgnoreCase(login) && usuarios[i].getPassword().equals(password)){
                return menus[i];
            }
        }

        return null;
    }
}
