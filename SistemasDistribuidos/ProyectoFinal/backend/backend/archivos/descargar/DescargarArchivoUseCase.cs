using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class DescargarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public DescargarArchivoUseCase (EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
                BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }
          
        public async Task<Blob> descargar(Guid archivoId, Guid usuarioId, Guid blobId, bool ultimaVersion = true) {
            this.ensureArchivoExistsAndOwnsArchivo(archivoId, usuarioId);

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

        private async void ensureArchivoExistsAndOwnsArchivo(Guid archivoId, Guid usuarioId) {
            Archivo archivo = this.archivosRepository.findById(archivoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("Archivo no encontrado");
            }
            if(archivo.esCarpeta) {
                throw new IllegalState("No se pueden descargar carpetas");
            }
            if (!await this.espacioTrabajoPermisosService.puedeLeer(archivo.espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("Este espacio trabajo no te corresponde");
            }
        }
    }
}
