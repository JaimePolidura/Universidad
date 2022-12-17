namespace backend.archivos._shared.espaciotrabajos {
    public class EspacioTrabajoPermisosService {
        private readonly EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public EspacioTrabajoPermisosService(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }

        public bool puedeLeer(Guid espacioTrabajoId, Guid usuarioId) {
            EspacioTrabajo espacioTrabajo = this.espacioTrabajoRepositorio.findById(espacioTrabajoId);

            return espacioTrabajo.usuarioId.Equals(usuarioId);
        }

        public bool puedeEscribir(Guid espacioTrabajoId, Guid usuarioId) {
            EspacioTrabajo espacioTrabajo = this.espacioTrabajoRepositorio.findById(espacioTrabajoId);

            return espacioTrabajo.usuarioId.Equals(usuarioId);
        }
    }
}
