using backend.archivos;

namespace backend.archivos {
    public interface EspacioTrabajoRepositorio {
        void save(EspacioTrabajo espacioTrabajo);

        Task<EspacioTrabajo> findById(Guid espacioTrabajoId);

        Task<List<EspacioTrabajo>> findByUsuarioId(Guid usuarioId, bool incluirBorrados);
    }
}
