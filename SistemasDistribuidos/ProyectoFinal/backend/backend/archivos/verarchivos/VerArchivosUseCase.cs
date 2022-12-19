using backend.archivos._comun.archivos;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class VerArchivosUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivoResponseCreator archivoResponseCreator;
        private readonly ArchivosRepository archivosRepository;

        public VerArchivosUseCase(ArchivosRepository archivosRepository, ArchivoResponseCreator archivoResponseCreator, 
            EspacioTrabajoPermisosService espacioTrabajoPermisosService) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivoResponseCreator = archivoResponseCreator;
            this.archivosRepository = archivosRepository;
        }
           
        public async Task<List<ArchivoResponse>> verArchivos(Guid archivoPadreId, Guid espacioTrabajoId, Guid usuarioId, bool incluirBorrados) {
            this.espacioTrabajoPermisosService.puedeLeerOrThrow(espacioTrabajoId, usuarioId);

            List<Archivo> archivos = archivoPadreId == Guid.Empty ?
                await this.archivosRepository.findRootByEspacioTrabajoId(espacioTrabajoId, incluirBorrados) :
                await this.archivosRepository.findChildrenByParentId(archivoPadreId, espacioTrabajoId, incluirBorrados);

            return (await Task.WhenAll(archivos.Select(it => this.archivoResponseCreator.createFromArchivo(it)).ToArray()))
                .ToList();
        }
    }
}
