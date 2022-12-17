namespace backend.archivos._shared.blobs {
    public interface BlobRepository {
        void save(Blob blob);

        Blob findByArchivoIdAndLastVersion(Guid archivoId);
    }
}
