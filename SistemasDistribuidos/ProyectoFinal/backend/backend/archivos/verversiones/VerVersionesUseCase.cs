using backend.archivos._comun.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class VerVersionesUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;
        private readonly ArchivoFinder archivoFinder;

        public VerVersionesUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            BlobRepository blobRepository, ArchivoFinder archivoFinder) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
            this.archivoFinder = archivoFinder;
        }

        public async Task<List<VersionArchivoBlob>> ver(Guid arhivoId, Guid ususarioId) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(arhivoId);
            this.espacioTrabajoPermisosService.puedeLeerOrThrow(archivo.espacioTrabajoId, ususarioId);
            List<Blob> blobsArchivo = await this.blobRepository.findByArchivoId(arhivoId);

            return blobsArchivo
                .Select(it => new VersionArchivoBlob(it.blobId, it.archivoId, it.fechaCreacion, it.usuarioIdCreacion, it.formato, it.nombre))
                .ToList();
        }
    }
}
