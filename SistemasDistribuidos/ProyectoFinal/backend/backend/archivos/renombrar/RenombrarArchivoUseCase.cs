using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.renombrar;

namespace backend.archivos {
    public class RenombrarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivoResponseCreator archivoResponseCreator;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;
        private readonly ArchivoFinder archivoFinder;

        public RenombrarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, BlobRepository blobRepository, 
            ArchivoFinder archivoFinder, ArchivoResponseCreator archivoResponseCreator) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivoResponseCreator = archivoResponseCreator;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
            this.archivoFinder = archivoFinder;
        }
        
        public async Task<ArchivoResponse> renombrar(RenombrarArchivoRequest request, Guid usuarioId) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(request.archivoId);
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(archivo.espacioTrabajoId, usuarioId);
            this.ensureNombreValid(request.nuevoNombre);
            
            if(archivo.esCarpeta) {
                archivo.nombreCarpeta = request.nuevoNombre;
                this.archivosRepository.save(archivo);
            }

            if (!archivo.esCarpeta) {
                Blob blob = await this.blobRepository.findByArchivoIdAndLastVersion(archivo.archivoId);
                blob.nombre = request.nuevoNombre;
                this.blobRepository.save(blob);
            }

            return await this.archivoResponseCreator.createFromArchivo(archivo);
        }

        private void ensureNombreValid(string nuevoNombre) {
            if (nuevoNombre == null || nuevoNombre.Length == 0 || nuevoNombre.Length > 128) {
                throw new IllegalState("Nombre invalido");
            }
        }
    }
}
