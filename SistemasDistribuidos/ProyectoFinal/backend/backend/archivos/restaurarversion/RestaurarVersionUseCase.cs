using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.restaurarversion;

namespace backend.archivos {
    public class RestaurarVersionUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly BlobRepository blobRepository;
        private readonly ArchivoFinder archivoFinder;

        public RestaurarVersionUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, BlobRepository blobRepository, 
            ArchivoFinder archivoFinder) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.blobRepository = blobRepository;
            this.archivoFinder = archivoFinder;
        }

        public async Task<VersionArchivoBlob> restaurar(RestaurarVersionRequest request, Guid usuarioId) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(request.archivoId);
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(archivo.espacioTrabajoId, usuarioId);
            Blob blobARestaurar = await this.blobRepository.findByBlobId(request.blobIdRestaurar);
            this.ensureBlobExistsAndBelongsToArchivo(blobARestaurar, request.archivoId);

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
    }
}
