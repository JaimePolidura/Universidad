namespace backend.archivos._shared.espaciotrabajos {
    public class EspacioTrabajoPermisosService {
        private readonly EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public EspacioTrabajoPermisosService(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }

        public async Task<bool> puedeLeer(Guid espacioTrabajoId, Guid usuarioId) {
            EspacioTrabajo espacioTrabajo = await this.espacioTrabajoRepositorio.findById(espacioTrabajoId);

            return espacioTrabajo != null && espacioTrabajo.usuarioId.Equals(usuarioId);
        }
         
        public async Task<bool> puedeEscribir(Guid espacioTrabajoId, Guid usuarioId) {
            EspacioTrabajo espacioTrabajo = await this.espacioTrabajoRepositorio.findById(espacioTrabajoId);

            return espacioTrabajo != null && espacioTrabajo.usuarioId.Equals(usuarioId);
        }
    }
}
