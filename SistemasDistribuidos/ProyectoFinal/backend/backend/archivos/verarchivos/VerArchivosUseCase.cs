using backend._shared.expceptions;

namespace backend.archivos {
    public class VerArchivosUseCase {
        private readonly EspacioTrabajoRepositorio espacioTrabajoRepositorio;
        private readonly ArchivosRepository archivosRepository;
        
        public VerArchivosUseCase(ArchivosRepository archivosRepository, EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
            this.archivosRepository = archivosRepository;
        }
        
        public List<Archivo> verArchivos(Guid archivoPadreId, Guid espacioTrabajoId, Guid usuarioId, bool incluirBorrados) {
            this.ensureUserOwnsEspacioTrabajo(espacioTrabajoId, usuarioId);
              
            return archivoPadreId == Guid.Empty ?
                this.archivosRepository.findRootByEspacioTrabajoId(espacioTrabajoId, incluirBorrados) :
                this.archivosRepository.findChildrenByParentId(archivoPadreId, espacioTrabajoId, incluirBorrados);
        }

        private void ensureUserOwnsEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            EspacioTrabajo espacio = this.espacioTrabajoRepositorio.findById(espacioTrabajoId);
               
            if (espacio == null)
                throw new ResourceNotFound("No se encuentra el espacio de trabajo");
            if (espacio.usuarioId.Equals(usuarioId) == false)
                throw new NotTheOwner("El espacio de trabajo no le pertenece");
        }
    }
}
