namespace backend.archivos._shared.blobs {
    public interface BlobRepository {
        void save(Blob blob);

        Task<Blob> findByBlobId(Guid blobId);

        Task<List<Blob>> findByArchivoId(Guid archivoId);

        Task<Blob> findByArchivoIdAndLastVersion(Guid archivoId);

        void deleteByArchivoIdAndMoreThanFechaCreacion(Guid archivoId, DateTime creationDate);
    }
}
