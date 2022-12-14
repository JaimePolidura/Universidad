namespace backend.archivos {
    public class EspacioTrabajo {
        public Guid espacioTrabajoId { get; }
        public Guid usuarioId { get; }
        public string nombre { get; }
        public DateTime fechaCreacion { get; }
        public bool borrado { get; }
        public DateTime? fechaBorrado { get; }

        public EspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId, string nombre, 
            DateTime fechaCreacion, bool borrado, DateTime? fechaBorrado) {
            this.espacioTrabajoId = espacioTrabajoId;
            this.usuarioId = usuarioId;
            this.nombre = nombre;
            this.fechaCreacion = fechaCreacion;
            this.borrado = borrado;
            this.fechaBorrado = fechaBorrado;
        }
    }
}
