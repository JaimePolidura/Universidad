namespace backend.archivos {
    public class Archivo {
        public Guid archivoId { get; set; }
        public Guid espacioTrabajoId { get; set; }
        public bool borrado { get; set;  }
        public DateTime fechaCreacion { get; set; }
        public DateTime? fechaBorrado { get; set; }
        public Guid usuarioIdBorrado { get; set; }
        public Guid archivoPadreId { get; set; }
        public bool esCarpeta { get; set; }
        public string? nombreCarpeta { get; set; }
            
        public Archivo(Guid archivoId, Guid espacioTrabajoId, bool borrado, DateTime fechaCreacion, DateTime? fechaBorrado, Guid usuarioIdBorrado,
            Guid archivoPadreId, bool esCarpeta, string nombreCarpeta) {
            this.archivoId = archivoId;
            this.espacioTrabajoId = espacioTrabajoId;
            this.fechaCreacion = fechaCreacion;
            this.borrado = borrado;
            this.fechaBorrado = fechaBorrado;
            this.usuarioIdBorrado = usuarioIdBorrado;
            this.archivoPadreId = archivoPadreId;
            this.esCarpeta = esCarpeta;
            this.nombreCarpeta = nombreCarpeta;
        }
    }
}
