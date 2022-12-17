namespace backend.archivos.subirarchivo {
    public class SubirNuevoArchivoRequest {
        public IFormFile blob { get; set; }
        public Guid archivoPadreId { get; set; }
        public Guid espacioTrabajoId { get; set; }
    }
}
