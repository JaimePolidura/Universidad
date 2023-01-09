namespace backend.usuarios._shared
{
    public class Usuario
    {
        public Guid usuarioId { get; }
        public string nombre { get; }
        public string claves { get; }

        public Usuario(Guid usuarioId, string nombre, string claves)
        {
            this.usuarioId = usuarioId;
            this.nombre = nombre;
            this.claves = claves;
        }
    }
}
