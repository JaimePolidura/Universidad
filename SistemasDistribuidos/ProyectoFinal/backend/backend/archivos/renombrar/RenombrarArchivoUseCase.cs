using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.renombrar;

namespace backend.archivos {
    public class RenombrarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public RenombrarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }

        public Archivo renombrar(RenombrarArchivoRequest request, Guid usuarioId) {
            Archivo archivo = this.ensureArchivoExists(request.archivoId);
            this.ensureHasPermissionsInEspacioTrabajo(archivo.espacioTrabajoId, usuarioId);
            this.ensureNombreValid(request.nuevoNombre);

            archivo.nombre = request.nuevoNombre;
            this.archivosRepository.save(archivo);

            if (!archivo.esCarpeta) {
                Blob blob = this.blobRepository.findByArchivoIdAndLastVersion(archivo.archivoId);
                blob.nombre = request.nuevoNombre;
                this.blobRepository.save(blob);
            }

            return archivo;
        }

        private void ensureNombreValid(string nuevoNombre) {
            if (nuevoNombre == null || nuevoNombre.Length == 0 || nuevoNombre.Length > 128) {
                throw new IllegalState("Nombre invalido");
            }
        }

        private void ensureHasPermissionsInEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            if (!this.espacioTrabajoPermisosService.puedeEscribir(espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos en ese espacio de trabajo");
            }
        }

        private Archivo ensureArchivoExists(Guid archivoId) {
            Archivo archivo = this.archivosRepository.findById(archivoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("No se encuentra el archivo");
            }

            return archivo;
        }
    }
}
