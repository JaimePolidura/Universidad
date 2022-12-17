namespace backend.archivos._shared.blobs {
    public class InMemoryBlobRepository : BlobRepository {
        private readonly List<Blob> blobs = new List<Blob>();

        public Blob findByArchivoIdAndLastVersion(Guid archivoId) {
            return this.blobs.Where(it => it.archivoId.Equals(archivoId))
                    .OrderBy(blob => blob.fechaCreacion)
                    .Last();
        }

        public void save(Blob blob) {
            int index = this.blobs.FindIndex(it => it.blobId.Equals(blob.blobId));

            if (index != -1)
                this.blobs.RemoveAt(index);

            this.blobs.Add(blob);
        }
    }
}
