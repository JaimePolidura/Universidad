namespace backend.archivos {
    public class VerEspacioTrabajosUseCase {
        private EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public VerEspacioTrabajosUseCase(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }

        public List<EspacioTrabajo> ver(Guid usuarioId) {
            return this.espacioTrabajoRepositorio.findByUsuarioId(usuarioId);
        }
    }
}
