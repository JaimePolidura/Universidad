using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class VerVersionesUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public VerVersionesUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }

        public List<VersionArchivoBlob> ver(Guid arhivoId, Guid ususarioId) {
            Archivo archivo = this.EnsureUsuariosOwnsArchivo(arhivoId);
            this.ensureHasPermissionesInEspacioTrabajo(archivo.espacioTrabajoId, ususarioId);

            List<Blob> blobsArchivo = this.blobRepository.findByArchivoId(arhivoId);

            return blobsArchivo
                .Select(it => new VersionArchivoBlob(it.blobId, it.archivoId, it.fechaCreacion, it.usuarioIdCreacion, it.formato, it.nombre))
                .ToList();
        }

        private void ensureHasPermissionesInEspacioTrabajo(Guid espacioTrabajoId, Guid ususarioId) {
            if (!this.espacioTrabajoPermisosService.puedeLeer(espacioTrabajoId, ususarioId)) {
                throw new NotTheOwner("No tienes permisos");
            }
        }

        private Archivo EnsureUsuariosOwnsArchivo(Guid arhivoId) {
            Archivo archivo = this.archivosRepository.findById(arhivoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("No se ha encontrado el archivo");
            }

            return archivo;
        }
    }
}
