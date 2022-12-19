using backend._shared;
using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos.reemplazararchivo {
    public class ReemplazarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public ReemplazarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository) {
           this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
           this.archivosRepository = archivosRepository;
           this.blobRepository = blobRepository;
        }

        public async Task<Archivo> reemplazar(ReemplazarArchivoRequest request, Guid usuarioId) {
            Archivo archivo = await this.ensureArchivoExists(request.archivoId);
            this.ensureHasPermissionsInEspacioTrabajo(archivo.espacioTrabajoId, usuarioId);

            Blob blobNuevoArchivo = new Blob(
                blobId: Guid.NewGuid(),
                archivoId: archivo.archivoId,
                binario: BytesReader.readFromFormFile(request.blob),
                fechaCreacion: DateTime.Now,
                usuarioIdCreacion: usuarioId,
                nombre: request.blob.FileName,
                formato: request.blob.ContentType);

            archivo.nombre = request.blob.FileName;
            archivo.formato = request.blob.ContentType;

            this.archivosRepository.save(archivo);
            this.blobRepository.save(blobNuevoArchivo);
            
            return archivo;
        }
            
        private async void ensureHasPermissionsInEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            if (!await this.espacioTrabajoPermisosService.puedeEscribir(espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("Espacio trabajo no encontrado / No tienes permisos");
            }
        }

        private async Task<Archivo> ensureArchivoExists(Guid archivoId) {
            Archivo archivo = await this.archivosRepository.findById(archivoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("Archivo no encontrado");
            }

            return archivo;
        }
    }
}
