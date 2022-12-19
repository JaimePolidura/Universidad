using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class DescargarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly BlobRepository blobRepository;
        private readonly ArchivoFinder archivoFinder;

        public DescargarArchivoUseCase (EspacioTrabajoPermisosService espacioTrabajoPermisosService, BlobRepository blobRepository, 
            ArchivoFinder archivoFinder) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.blobRepository = blobRepository;
            this.archivoFinder = archivoFinder;
        }
          
        public async Task<Blob> descargar(Guid archivoId, Guid usuarioId, Guid blobId, bool ultimaVersion = true) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(archivoId);
            this.ensureArchivoIsNotCarpeta(archivo);
            this.espacioTrabajoPermisosService.puedeLeerOrThrow(archivo.espacioTrabajoId, usuarioId);

            if (ultimaVersion) {
                return await this.blobRepository.findByArchivoIdAndLastVersion(archivoId);
            }

            Blob blob = await this.blobRepository.findByBlobId(blobId);
            this.ensureBlobExistsAndBelongsToArchivo(blob, archivoId);
            
            return blob;
        }

        private void ensureBlobExistsAndBelongsToArchivo(Blob blob, Guid archivoId) {
            if(blob == null) {
                throw new ResourceNotFound("No se ha encontrado el archivo");
            }
            if (!blob.archivoId.Equals(archivoId)) {
                throw new NotTheOwner("Archivo incorrecto");
            }
        }

        private void ensureArchivoIsNotCarpeta(Archivo archivo) {
            if(archivo.esCarpeta) {
                throw new IllegalState("No se pueden descargar carpetas");
            }
        }
    }
}
