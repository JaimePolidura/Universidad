using backend._shared;
using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.subirarchivo;

namespace backend.archivos {
    public class SubirNuevoArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivoResponseCreator archivoResponseCreator;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public SubirNuevoArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository, ArchivoResponseCreator archivoResponseCreator) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
            this.archivoResponseCreator = archivoResponseCreator;
        }

        public async Task<ArchivoResponse> subirNuevoArchivo(SubirNuevoArchivoRequest request, Guid usuarioId) {
            this.ensureArchivoPadreExists(request);
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(request.espacioTrabajoId, usuarioId);

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
                nombreCarpeta: null);
                    
            Blob blob = new Blob(
                blobId: Guid.NewGuid(),
                archivoId: archivoId,
                binario: BytesReader.readFromFormFile(request.blob),
                fechaCreacion: now,
                usuarioIdCreacion: usuarioId,
                nombre: request.blob.FileName,
                formato: request.blob.ContentType
            );

            this.archivosRepository.save(archivo);
            this.blobRepository.save(blob);

            return await this.archivoResponseCreator.createFromArchivo(archivo);
        }

        private async void ensureArchivoPadreExists(SubirNuevoArchivoRequest request) {
            if (request.archivoPadreId == Guid.Empty) {
                return; //Subir al principio
            }

            Archivo archivo = await this.archivosRepository.findById(request.archivoPadreId, false);
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
