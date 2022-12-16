namespace backend.archivos {
    public class VerEspacioTrabajosUseCase {
        private EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public VerEspacioTrabajosUseCase(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }

        public List<EspacioTrabajo> ver(Guid usuarioId, bool incluirBorrados) {
            return this.espacioTrabajoRepositorio.findByUsuarioId(usuarioId, incluirBorrados);
        }
    }
}
