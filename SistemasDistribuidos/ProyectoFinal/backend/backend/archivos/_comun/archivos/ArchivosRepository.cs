namespace backend.archivos {
    public interface ArchivosRepository {
        void save(Archivo archivo);

        Task<Archivo> findById(Guid archivoId, bool incluirBorrados);

        Task<Archivo> findById(Guid archivoId, Guid espacioTrabajoId, bool incluirBorrados);

        Task<List<Archivo>> findRootByEspacioTrabajoId(Guid espacioTrabajoId, bool incluirBorrados);
           
        Task<List<Archivo>> findChildrenByParentId(Guid archivoId, Guid espacioTrabajoId, bool incluirBorrados);
    }
}
