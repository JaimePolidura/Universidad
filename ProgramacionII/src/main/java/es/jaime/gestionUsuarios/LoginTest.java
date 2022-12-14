package es.jaime.gestionUsuarios;

public class LoginTest {
    public static void main(String[] args) {
        SistemaGestionUsuarios userManager = new SistemaGestionUsuarios(10);

        userManager.addUsuario(new Usuario("hola", "hola", "123"), new Menu(1));

        // Las dos deberian ser true
        System.out.println(userManager.autentificaUsuario("hola", "123") != null);
        System.out.println(userManager.autentificaUsuario("hola", "sas12") == null);
    }
}
