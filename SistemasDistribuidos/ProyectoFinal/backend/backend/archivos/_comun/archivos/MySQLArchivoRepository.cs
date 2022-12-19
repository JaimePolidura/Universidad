using backend._shared;
using MySql.Data.MySqlClient;

namespace backend.archivos._shared.archivos {
    public class MySQLArchivoRepository : ArchivosRepository {
        private readonly MySQLService mySQLService;

        public MySQLArchivoRepository(MySQLService mySQLService) {
            this.mySQLService = mySQLService;
        }

        public void save(Archivo archivo) {
            this.mySQLService.sendCommand($"DELETE FROM archivos WHERE archivoId = '{archivo.archivoId}'");
            this.mySQLService.sendCommand($"INSERT INTO archivos (archivoId, espacioTrabajoId, borrado, fechaCreacion, fechaBorrado, usuarioIdBorrado, " +
                $"archivoPadreId, esCarpeta, nombre, formato) VALUES ('{archivo.archivoId}', '{archivo.espacioTrabajoId}', {archivo.borrado}, '{mySQLService.dateTimeToMySQLFormat(archivo.fechaCreacion)}', '{mySQLService.dateTimeToMySQLFormat(archivo.fechaBorrado)}', " +
                $"'{archivo.usuarioIdBorrado}', '{archivo.archivoPadreId}', {archivo.esCarpeta}, '{archivo.nombre}', '{archivo.formato}')");
        }

        public Task<Archivo> findById(Guid archivoId, bool incluirBorrados) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM archivos WHERE archivoId = '{archivoId}' {getIncludeBorradesQueryPart(incluirBorrados)}",
                this.archivoConstructor());
        }

        public Task<Archivo> findById(Guid archivoId, Guid espacioTrabajoId, bool incluirBorrados) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM archivos WHERE archivoId = '{archivoId}' AND espacioTrabajoId = '{espacioTrabajoId}' {getIncludeBorradesQueryPart(incluirBorrados)}", this.archivoConstructor());
        }

        public Task<List<Archivo>> findChildrenByParentId(Guid archivoPadreId, Guid espacioTrabajoId, bool incluirBorrados) {
            return this.mySQLService.sendSelectList($"SELECT * FROM archivos WHERE archivoPadreId = '{archivoPadreId}' AND espacioTrabajoId =  '{espacioTrabajoId}' {getIncludeBorradesQueryPart(incluirBorrados)}", this.archivoConstructor());
        }

        public Task<List<Archivo>> findRootByEspacioTrabajoId(Guid espacioTrabajoId, bool incluirBorrados) {
            return this.mySQLService.sendSelectList($"SELECT * FROM archivos WHERE espacioTrabajoId = '{espacioTrabajoId}' AND archivoPadreId = '00000000-0000-0000-0000-000000000000' {getIncludeBorradesQueryPart(incluirBorrados)}", this.archivoConstructor());
        }

        private string getIncludeBorradesQueryPart(bool incluirBorrados) {
            return incluirBorrados ? "" : "AND borrado IS FALSE"; ;
        }

        private Func<MySqlDataReader, Archivo> archivoConstructor() {
            return r => new Archivo(
                r.GetGuid("archivoId"),
                r.GetGuid("espacioTrabajoId"),
                r.GetBoolean("borrado"),
                r.GetDateTime("fechaCreacion"),
                this.parseDateTime(r.GetString("fechaBorrado")),
                r.GetGuid("usuarioIdBorrado"),
                r.GetGuid("archivoPadreId"),
                r.GetBoolean("esCarpeta"),
                r.GetString("nombre"),
                r.GetString("formato"));
        }

        private DateTime? parseDateTime(string raw) {
            return raw.Equals("") ? null : DateTime.Parse(raw);
        }
    }
}
