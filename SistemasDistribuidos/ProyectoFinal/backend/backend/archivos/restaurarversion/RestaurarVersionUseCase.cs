using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.restaurarversion;

namespace backend.archivos {
    public class RestaurarVersionUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public RestaurarVersionUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }

        public async Task<VersionArchivoBlob> restaurar(RestaurarVersionRequest request, Guid usuarioId) {
            Archivo archivo = await this.archivosRepository.findById(request.archivoId, false);
            this.ensureArchivoExistsAndHasPermissions(archivo, usuarioId);
            Blob blobARestaurar = await this.blobRepository.findByBlobId(request.blobIdRestaurar);
            this.ensureBlobExistsAndBelongsToArchivo(blobARestaurar, request.archivoId);

            archivo.nombre = blobARestaurar.nombre;
            archivo.formato = blobARestaurar.formato;

            this.blobRepository.deleteByArchivoIdAndMoreThanFechaCreacion(archivo.archivoId, blobARestaurar.fechaCreacion);

            return new VersionArchivoBlob(request.blobIdRestaurar, archivo.archivoId, blobARestaurar.fechaCreacion, 
                blobARestaurar.usuarioIdCreacion, blobARestaurar.formato, blobARestaurar.nombre);
        }

        private void ensureBlobExistsAndBelongsToArchivo(Blob blobARestaurar, Guid archivoId) {
            if (blobARestaurar == null) {
                throw new ResourceNotFound("No se encuentra el archivo");
            }
            if (!blobARestaurar.archivoId.Equals(archivoId)) {
                throw new NotTheOwner("Archovo incorrecto");
            }
        }

        private async void ensureArchivoExistsAndHasPermissions(Archivo archivo, Guid usuarioId) {
            if (archivo == null) {
                throw new ResourceNotFound("No se encuentra el archivo");
            }
            if (!await this.espacioTrabajoPermisosService.puedeEscribir(archivo.espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos");
            }
        }
    }
}
