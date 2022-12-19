using backend._shared;
using MySql.Data.MySqlClient;
using System.Data;

namespace backend.archivos._shared.blobs {
    public class MySQLBlobRepository : BlobRepository {
        private readonly MySQLService mySQLService;

        public MySQLBlobRepository(MySQLService mySQLService) {
            this.mySQLService = mySQLService;
        }

        public void save(Blob blob) {
            this.mySQLService.sendCommand($"DELETE FROM blobs WHERE blobId = '{blob.blobId}'");
            MySqlCommand insert = this.mySQLService.createCommand("INSERT INTO blobs (blobId, archivoId, binario, fechaCreacion, usuarioIdCreacion, formato, nombre) VALUES (@blobId, @archivoId, @binario, @fechaCreacion, @usuarioIdCreacion, @formato, @nombre)");

            insert.Parameters.Add("blobId", MySqlDbType.VarChar).Value = blob.blobId.ToString();
            insert.Parameters.Add("archivoId", MySqlDbType.VarChar).Value = blob.archivoId.ToString();
            insert.Parameters.Add("binario", MySqlDbType.VarBinary).Value = blob.binario;
            insert.Parameters.Add("fechaCreacion", MySqlDbType.DateTime).Value = blob.fechaCreacion;
            insert.Parameters.Add("usuarioIdCreacion", MySqlDbType.VarChar).Value = blob.usuarioIdCreacion.ToString();
            insert.Parameters.Add("formato", MySqlDbType.VarChar).Value = blob.formato.ToString();
            insert.Parameters.Add("nombre", MySqlDbType.VarChar).Value = blob.nombre.ToString();

            insert.ExecuteNonQuery();
        }

        public Task<List<Blob>> findByArchivoId(Guid archivoId) {
            return this.mySQLService.sendSelectList($"SELECT * FROM blobs WHERE archivoId = '{archivoId}' ORDER BY fechaCreacion DESC", this.blobConstructor());
        }

        public Task<Blob> findByArchivoIdAndLastVersion(Guid archivoId) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM blobs WHERE archivoId = '{archivoId}' ORDER BY fechaCreacion DESC LIMIT 1",
                this.blobConstructor());
        }

        public Task<Blob> findByBlobId(Guid blobId) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM blobs WHERE blobId = '{blobId}'", this.blobConstructor());
        }

        public void deleteByArchivoIdAndMoreThanFechaCreacion(Guid archivoId, DateTime fechaCreacion) {
            this.mySQLService.sendCommand($"DELETE FROM blobs WHERE archivoId = '{archivoId}' AND fechaCreacion > '{this.mySQLService.dateTimeToMySQLFormat(fechaCreacion)}'");
        }

        private Func<MySqlDataReader, Blob> blobConstructor() {
            return r => new Blob(r.GetGuid("blobId"),
                r.GetGuid("archivoId"),
                ((byte[]) r.GetValue("binario")),
                r.GetDateTime("fechaCreacion"),
                r.GetGuid("usuarioIdCreacion"),
                r.GetString("formato"),
                r.GetString("nombre")
                );
        }
    }
}
