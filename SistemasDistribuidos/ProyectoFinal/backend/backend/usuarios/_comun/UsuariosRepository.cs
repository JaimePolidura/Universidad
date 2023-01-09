namespace backend.usuarios._shared {
    public interface UsuariosRepository {
        void save(Usuario usuario);

        Task<Usuario> findById(Guid usuarioId);
          
        Task<Usuario> findByNombreAndClaves(string nombre, string claves);
    }
}
