using backend.archivos._shared.blobs;

namespace backend.archivos._comun.archivos {
    public class ArchivoResponseCreator {
        private readonly BlobRepository blobRepository;

        public ArchivoResponseCreator(BlobRepository blobRepository) {
            this.blobRepository = blobRepository;
        }

        public Task<ArchivoResponse> createFromArchivo(Archivo archivo) {
            return archivo.esCarpeta ?
                this.buildCarpeta(archivo) :
                this.buildNoCarpeta(archivo);
        }

        private async Task<ArchivoResponse> buildNoCarpeta(Archivo archivo) {
            Blob lastBlob = await this.blobRepository.findByArchivoIdAndLastVersion(archivo.archivoId);

            return new ArchivoResponse(archivo.archivoId, archivo.espacioTrabajoId, archivo.borrado, archivo.fechaCreacion, archivo.fechaBorrado, archivo.usuarioIdBorrado, archivo.archivoPadreId, false, lastBlob.nombre, lastBlob.formato);
        }

        private Task<ArchivoResponse> buildCarpeta(Archivo archivo) {
            return Task.FromResult(new ArchivoResponse(archivo.archivoId, archivo.espacioTrabajoId, archivo.borrado, archivo.fechaCreacion, archivo.fechaBorrado, archivo.usuarioIdBorrado, archivo.archivoPadreId, archivo.esCarpeta, archivo.nombreCarpeta, ""));
        }
    }
}
