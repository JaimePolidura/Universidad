using backend._shared.expceptions;

namespace backend.archivos._comun.archivos {
    public class ArchivoFinder {
        private ArchivosRepository archivosRepository;

        public ArchivoFinder(ArchivosRepository archivosRepository) {
            this.archivosRepository = archivosRepository;
        }

        public async Task<Archivo> findByIdOrThrow(Guid arhivoId, Guid espacioTrabajoId) {
            Archivo archivo = await this.archivosRepository.findById(arhivoId, false);

            if (archivo == null || !archivo.espacioTrabajoId.Equals(espacioTrabajoId)) {
                throw new ResourceNotFound("No se ha encontrado el archivo");
            }

            return archivo;
        }

        public async Task<Archivo> findByIdOrThrow(Guid arhivoId) {
            Archivo archivo = await this.archivosRepository.findById(arhivoId, false);

            if (archivo == null) {
                throw new ResourceNotFound("No se ha encontrado el archivo");
            }

            return archivo;
        }
    }
}
