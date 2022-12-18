namespace backend.archivos._shared.blobs {
    public class VersionArchivoBlob {
        public Guid blobId { get; }
        public Guid archivoId { get; }
        public DateTime fechaCreacion { get; }
        public Guid usuarioIdCreacion { get; }
        public string formato { get; }
        public string nombre { get; set; }

        public VersionArchivoBlob(Guid blobId, Guid archivoId, DateTime fechaCreacion, Guid usuarioIdCreacion, string formato, string nombre) {
            this.blobId = blobId;
            this.archivoId = archivoId;
            this.fechaCreacion = fechaCreacion;
            this.usuarioIdCreacion = usuarioIdCreacion;
            this.formato = formato;
            this.nombre = nombre;
        }
    }
}
