namespace backend.archivos {
    public class Archivo {
        public Guid archivoId { get; }
        public Guid espacioTrabajoId { get; }
        public bool borrado { get; }
        public DateTime fechaBorrado { get; }
        public Guid usuarioIdBorrado { get; }
        public Guid archivoPadreId { get; }
        public bool esCarpeta { get; }
        public string nombre { get; }
        public string formato { get; }

        public Archivo(Guid archivoId, Guid espacioTrabajoId, bool borrado, DateTime fechaBorrado, Guid usuarioIdBorrado,
            Guid archivoPadreId, bool esCarpeta, string nombre, string formato) {
            this.archivoId = archivoId;
            this.espacioTrabajoId = espacioTrabajoId;
            this.borrado = borrado;
            this.fechaBorrado = fechaBorrado;
            this.usuarioIdBorrado = usuarioIdBorrado;
            this.archivoPadreId = archivoPadreId;
            this.esCarpeta = esCarpeta;
            this.nombre = nombre;
            this.formato = formato;
        }
    }
}
