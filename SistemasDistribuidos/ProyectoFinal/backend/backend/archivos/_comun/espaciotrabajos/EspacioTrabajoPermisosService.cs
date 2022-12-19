using backend._shared.expceptions;
using backend.usuarios._shared;

namespace backend.archivos._shared.espaciotrabajos {
    public class EspacioTrabajoPermisosService {
        private readonly EspacioTrabajoRepositorio espacioTrabajoRepositorio;

        public EspacioTrabajoPermisosService(EspacioTrabajoRepositorio espacioTrabajoRepositorio) {
            this.espacioTrabajoRepositorio = espacioTrabajoRepositorio;
        }


        public async void puedeEsciribirOrThrow(Guid espacioTrabajoId, Guid usuarioId) {
            if (!await this.puedeEscribir(espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos para escribir");
            }
        }

        public async void puedeLeerOrThrow(Guid espacioTrabajoId, Guid usuarioId) {
            if (!await this.puedeEscribir(espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos para leer");
            }
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
