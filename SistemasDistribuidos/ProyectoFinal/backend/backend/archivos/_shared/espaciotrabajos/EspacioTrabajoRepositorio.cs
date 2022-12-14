using backend.archivos;

namespace backend.archivos {
    public interface EspacioTrabajoRepositorio {
        void save(EspacioTrabajo espacioTrabajo);

        EspacioTrabajo findById(Guid espacioTrabajoId);
    }
}
