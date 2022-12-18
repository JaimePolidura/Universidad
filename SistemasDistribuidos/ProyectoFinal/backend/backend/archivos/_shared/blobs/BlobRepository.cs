namespace backend.archivos._shared.blobs {
    public interface BlobRepository {
        void save(Blob blob);

        Blob findByBlobId(Guid blobId);

        List<Blob> findByArchivoId(Guid archivoId);

        Blob findByArchivoIdAndLastVersion(Guid archivoId);
    }
}
