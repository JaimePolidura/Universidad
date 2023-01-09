using backend._shared;
using MySql.Data.MySqlClient;

namespace backend.usuarios._shared {
    public class MySQLUsuariosRepository : UsuariosRepository {
        private readonly MySQLService mySQLService;

        public MySQLUsuariosRepository(MySQLService mySQLService) {
            this.mySQLService = mySQLService;
        }

        public Task<Usuario> findById(Guid usuarioId) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM usuarios WHERE usuarioId = '{usuarioId}'", 
                this.usuarioConstructorFromDb());
        }

        public Task<Usuario> findByNombreAndClaves(string nombre, string claves) {
            return this.mySQLService.sendSelectOneObject($"SELECT * FROM usuarios WHERE nombre = '{nombre}' AND claves = '{claves}'",
                this.usuarioConstructorFromDb());
        }

        public void save(Usuario usuario) {
            this.mySQLService.sendCommand($"DELETE FROM usuarios WHERE usuarioId = '{usuario.usuarioId}'");
            this.mySQLService.sendCommand($"INSERT INTO usuarios (usuarioId, nombre, claves) VALUES ('{usuario.usuarioId}', '{usuario.nombre}', '{usuario.claves}')");
        }

        private Func<MySqlDataReader, Usuario> usuarioConstructorFromDb() {
            return reader => new Usuario(
                usuarioId: reader.GetGuid("usuarioId"),
                nombre: reader.GetString("nombre"),
                claves: reader.GetString("claves")
                );
        }
    }
}
