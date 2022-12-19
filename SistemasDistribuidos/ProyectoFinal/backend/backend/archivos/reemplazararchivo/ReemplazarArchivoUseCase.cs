using backend._shared;
using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos.reemplazararchivo {
    public class ReemplazarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;
        private readonly ArchivoFinder archivoFinder;

        public ReemplazarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository, ArchivoFinder archivoFinder) {
           this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
           this.archivosRepository = archivosRepository;
           this.blobRepository = blobRepository;
           this.archivoFinder = archivoFinder;
        }

        public async Task<Archivo> reemplazar(ReemplazarArchivoRequest request, Guid usuarioId) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(request.archivoId);
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(archivo.espacioTrabajoId, usuarioId);

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
    }
}
