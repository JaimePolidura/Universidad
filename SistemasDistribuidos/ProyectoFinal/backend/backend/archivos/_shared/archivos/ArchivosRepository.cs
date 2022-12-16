namespace backend.archivos {
    public interface ArchivosRepository {
        void save(Archivo archivo);

        Archivo findById(Guid archivoId, Guid espacioTrabajoId);

        List<Archivo> findRootByEspacioTrabajoId(Guid espacioTrabajoId, bool incluirBorrados);
           
        List<Archivo> findChildrenByParentId(Guid archivoId, Guid espacioTrabajoId, bool incluirBorrados);

        void deleteById(Guid archivoId, Guid espacioTrabajoId);
    }
}
