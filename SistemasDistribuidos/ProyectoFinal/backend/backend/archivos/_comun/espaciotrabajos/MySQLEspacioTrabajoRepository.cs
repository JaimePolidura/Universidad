using backend._shared;
using MySql.Data.MySqlClient;
using System.Data;

namespace backend.archivos._shared.espaciotrabajos {
    public class MySQLEspacioTrabajoRepository : EspacioTrabajoRepositorio {
        private readonly MySQLService mySQLService;

        public MySQLEspacioTrabajoRepository(MySQLService mySQLService) {
            this.mySQLService = mySQLService;
        }

        public void save(EspacioTrabajo espacioTrabajo) {
            this.mySQLService.sendCommand($"DELETE FROM espaciotrabajos WHERE espacioTrabajoId = '{espacioTrabajo.espacioTrabajoId}'");
            this.mySQLService.sendCommand($"INSERT INTO espaciotrabajos (espacioTrabajoId, usuarioId, nombre, fechaCreacion, borrado, fechaBorrado) VALUES (" +
                $"'{espacioTrabajo.espacioTrabajoId}', '{espacioTrabajo.usuarioId}', '{espacioTrabajo.nombre}', '{mySQLService.dateTimeToMySQLFormat(espacioTrabajo.fechaCreacion)}', '{espacioTrabajo.borrado}', '{mySQLService.dateTimeToMySQLFormat(espacioTrabajo.fechaBorrado)}')");
        }

        public Task<EspacioTrabajo> findById(Guid espacioTrabajoId) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM espaciotrabajos WHERE espacioTrabajoId = '{espacioTrabajoId}'",
                this.espacioTrabajoConstructor());
        }

        public Task<List<EspacioTrabajo>> findByUsuarioId(Guid usuarioId, bool incluirBorrados) {
            string queryBorrados = incluirBorrados ? "" : "AND borrado IS FALSE";
            return this.mySQLService.sendSelectList($"SELECT * FROM espaciotrabajos WHERE usuarioId = '{usuarioId}' {queryBorrados}",
                this.espacioTrabajoConstructor());
        }

        private Func<MySqlDataReader, EspacioTrabajo> espacioTrabajoConstructor() {
            return r => {
                return new EspacioTrabajo(
                r.GetGuid("espacioTrabajoId"),
                r.GetGuid("usuarioId"),
                r.GetString("nombre"),
                r.GetDateTime("fechaCreacion"),
                r.GetBoolean("borrado"),
                null);
            };
        }
    }
}
