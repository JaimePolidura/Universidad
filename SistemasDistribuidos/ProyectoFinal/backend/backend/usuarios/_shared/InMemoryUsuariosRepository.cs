namespace backend.usuarios._shared {
    public class InMemoryUsuariosRepository : UsuariosRepository {
        private readonly List<Usuario> usuarios = new List<Usuario>();

        public void save(Usuario usuario) {
            int index = this.usuarios.FindIndex(it => it.usuarioId.Equals(usuario.usuarioId));

            if (index != -1)
                this.usuarios.RemoveAt(index);

            this.usuarios.Add(usuario);
        }

        public Usuario findByNombreAndClaves(string nombre, string password) {
            return this.usuarios.Where(it => it.nombre.Equals(nombre) && it.claves.Equals(password)).FirstOrDefault();
        }

        public Usuario findById(Guid usuarioId) {
            return this.usuarios.Where(it => it.usuarioId.Equals(usuarioId)).First();
        }
    }
}
