namespace backend.usuarios._shared {
    public interface UsuariosRepository {
        void save(Usuario usuario);

        Usuario findById(Guid usuarioId);

        Usuario findByNombreAndClaves(string nombre, string claves);
    }
}
