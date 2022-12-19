using MySql.Data.MySqlClient;
using System.Data.Common;

namespace backend._shared {
    public class MySQLService {
        private MySqlConnection connection;
        private readonly IConfiguration configuration;

        public MySQLService(IConfiguration configuration) {
            this.configuration = configuration;
        }

        public Task startConnection() {
            string serverHost = this.configuration["db:host"];
            string port = this.configuration["db:port"];
            string user = this.configuration["db:user"];
            string password = this.configuration["db:password"];
            string dbName = this.configuration["db:name"];

            this.connection = new MySqlConnection(
                $"server={serverHost};port={port};userid={user};password={password};database={dbName}"
            );

            return this.connection.OpenAsync();
        }

        public async void sendCommand(string query) {
            MySqlCommand command = new MySqlCommand(query, connection);
            await command.ExecuteNonQueryAsync();
        }

        public MySqlCommand createCommand(string query) {
            return new MySqlCommand(query, this.connection);
        }

        public async void sendCommand(MySqlCommand command) {
            await command.ExecuteNonQueryAsync();
        }

        public async Task<List<T>> sendSelectList<T>(string query, Func<MySqlDataReader, T> objectConstructor) {
            MySqlCommand command = new MySqlCommand(query, connection);
            MySqlDataReader reader = (MySqlDataReader) await command.ExecuteReaderAsync();
            List<T> toReturn = new List<T>();

            while(reader.Read()) {
                toReturn.Add(objectConstructor.Invoke(reader));
            }

            reader.Close();

            return toReturn;
        }

        public async Task<T> sendSelectOneObject<T>(string query, Func<MySqlDataReader, T> objectConstructor) {
            MySqlCommand command = new MySqlCommand(query, connection);
            MySqlDataReader reader = (MySqlDataReader) await command.ExecuteReaderAsync();

            reader.Read();

            T toReturn = objectConstructor.Invoke(reader);

            reader.Close();

            return toReturn;
        }
        
        public string dateTimeToMySQLFormat(DateTime? dateTime) {
            return dateTime?.ToString("yyyy-MM-dd HH:mm:ss") ?? null;
        }
    }
}
