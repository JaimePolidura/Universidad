namespace backend.archivos {
    public class Archivo {
        public Guid archivoId { get; set; }
        public Guid espacioTrabajoId { get; set; }
        public bool borrado { get; }
        public DateTime fechaCreacion { get; set; }
        public DateTime? fechaBorrado { get; set; }
        public Guid usuarioIdBorrado { get; set; }
        public Guid archivoPadreId { get; set; }
        public bool esCarpeta { get; set; }
        public string nombre { get; set; }
        public string formato { get; set; }

        public Archivo(Guid archivoId, Guid espacioTrabajoId, bool borrado, DateTime fechaCreacion, DateTime? fechaBorrado, Guid usuarioIdBorrado,
            Guid archivoPadreId, bool esCarpeta, string nombre, string formato) {
            this.archivoId = archivoId;
            this.espacioTrabajoId = espacioTrabajoId;
            this.fechaCreacion = fechaCreacion;
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
