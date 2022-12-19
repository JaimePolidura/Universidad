namespace backend.archivos {
    public class VerEspacioTrabajosUseCase {
        private EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public VerEspacioTrabajosUseCase(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }

        public async Task<List<EspacioTrabajo>> ver(Guid usuarioId, bool incluirBorrados) {
            return await this.espacioTrabajoRepositorio.findByUsuarioId(usuarioId, incluirBorrados);
        }
    }
}
