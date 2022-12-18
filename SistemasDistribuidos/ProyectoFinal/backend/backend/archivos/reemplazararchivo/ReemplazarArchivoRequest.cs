namespace backend.archivos.reemplazararchivo {
    public class ReemplazarArchivoRequest {
        public IFormFile blob { get; set; }
        public Guid archivoId { get; set; }
    }
}
