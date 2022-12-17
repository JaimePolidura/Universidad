using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.subirarchivo;

namespace backend.archivos {
    public class SubirNuevoArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public SubirNuevoArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }

        public Archivo subirNuevoArchivo(SubirNuevoArchivoRequest request, Guid usuarioId) {
            this.ensureArchivoPadreExists(request);
            this.ensureHasPermissionsInEspacioTrabajo(request, usuarioId);

            Guid archivoId = Guid.NewGuid();
            DateTime now = DateTime.Now;
                    
            Archivo archivo = new Archivo(
                archivoId: archivoId,
                espacioTrabajoId: request.espacioTrabajoId,
                borrado: false,
                fechaCreacion: now,
                fechaBorrado: null,
                usuarioIdBorrado: Guid.Empty,
                archivoPadreId: request.archivoPadreId,
                esCarpeta: false,
                nombre: request.blob.FileName,
                formato: request.blob.ContentType.ToString());

            Blob blob = new Blob(
                blobId: Guid.NewGuid(),
                archivoId: archivoId,
                binario: this.readBytes(request.blob),
                fechaCreacion: now,
                usuarioIdCreacion: usuarioId);

            this.archivosRepository.save(archivo);
            this.blobRepository.save(blob);

            return archivo;
        }

        private byte[] readBytes(IFormFile file) {
            byte[] fileBytes;
            using (var stream = file.OpenReadStream()) {
                fileBytes = new byte[stream.Length];
                stream.Read(fileBytes, 0, (int)stream.Length);
            }

            return fileBytes;
        }
           
        private void ensureHasPermissionsInEspacioTrabajo(SubirNuevoArchivoRequest request, Guid usuarioId) {
            if (!this.espacioTrabajoPermisosService.puedeEscribir(request.espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos para escribir en el espacio de trabajo");
            }
        }

        private void ensureArchivoPadreExists(SubirNuevoArchivoRequest request) {
            if (request.archivoPadreId == Guid.Empty) {
                return; //Subir al principio
            }

            Archivo archivo = this.archivosRepository.findById(request.archivoPadreId, false);
            if (archivo == null) {
                throw new ResourceNotFound("Archivo no encontrado");
            }
            if (!archivo.esCarpeta) {
                throw new IllegalState("No es una carpeta");
            }
            if (!archivo.espacioTrabajoId.Equals(request.espacioTrabajoId)) {
                throw new ResourceNotFound("Espacio de trabajo incorrecto");
            }
        }
    }
}
