namespace backend.archivos {
    public class Blob {
        public Guid blobId { get; }
        public Guid archivoId { get; }
        public byte[] binario { get; }
        public DateTime fechaCreacion { get; }
        public Guid usuarioIdCreacion { get; }

        public Blob(Guid blobId, Guid archivoId, byte[] binario, DateTime fechaCreacion, Guid usuarioIdCreacion) {
            this.blobId = blobId;
            this.archivoId = archivoId;
            this.binario = binario;
            this.fechaCreacion = fechaCreacion;
            this.usuarioIdCreacion = usuarioIdCreacion;
        }
    }
}
