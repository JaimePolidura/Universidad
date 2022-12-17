using backend._shared.expceptions;
using backend.archivos._shared.espaciotrabajos;

namespace backend.archivos {
    public class VerArchivosUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        
        public VerArchivosUseCase(ArchivosRepository archivosRepository, EspacioTrabajoPermisosService espacioTrabajoPermisosService) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
        }
        
        public List<Archivo> verArchivos(Guid archivoPadreId, Guid espacioTrabajoId, Guid usuarioId, bool incluirBorrados) {
            this.ensureUserOwnsEspacioTrabajo(espacioTrabajoId, usuarioId);
              
            return archivoPadreId == Guid.Empty ?
                this.archivosRepository.findRootByEspacioTrabajoId(espacioTrabajoId, incluirBorrados) :
                this.archivosRepository.findChildrenByParentId(archivoPadreId, espacioTrabajoId, incluirBorrados);
        }

        private void ensureUserOwnsEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            if(!this.espacioTrabajoPermisosService.puedeLeer(espacioTrabajoId, usuarioId)) {
                throw new ResourceNotFound("No se encuentra el espacio de trabajo / No tienes permisos");
            }
        }
    }
}
