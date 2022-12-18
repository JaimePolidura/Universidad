namespace backend.archivos.restaurarversion {
    public class RestaurarVersionRequest {
        public Guid archivoId { get; set; }
        public Guid blobIdRestaurar { get; set; }

        public RestaurarVersionRequest(Guid archivoId, Guid blobIdRestaurar) {
            this.archivoId = archivoId;
            this.blobIdRestaurar = blobIdRestaurar;
        }
    }
}
