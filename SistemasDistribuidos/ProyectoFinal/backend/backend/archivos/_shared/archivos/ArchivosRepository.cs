namespace backend.archivos {
    public interface ArchivosRepository {
        void save(Archivo archivo);

        Archivo findById(Guid archivoId, Guid espacioTrabajoId);

        List<Archivo> findRootByEspacioTrabajoId(Guid espacioTrabajoId);

        List<Archivo> findChildrenByParentId(Guid archivoId, Guid espacioTrabajoId);

        void deleteById(Guid archivoId, Guid espacioTrabajoId);
    }
}
