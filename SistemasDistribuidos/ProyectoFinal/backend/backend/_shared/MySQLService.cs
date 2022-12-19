using MySql.Data.MySqlClient;
using System.Data.Common;

namespace backend._shared {
    public class MySQLService {
        private MySqlConnection connection;
        
        public Task startConnection() {
            this.connection = new MySqlConnection(
                $"server=localhost;port=3306;userid=root;password=;database=archivos"
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
            List<T> result = new List<T>();

            while(reader.Read()) {
                result.Add(objectConstructor.Invoke(reader));
            }

            reader.Close();

            return result;
        }

        public async Task<T> sendSelectOneObject<T>(string query, Func<MySqlDataReader, T> objectConstructor) {
            MySqlCommand command = new MySqlCommand(query, connection);
            MySqlDataReader reader = (MySqlDataReader) await command.ExecuteReaderAsync();

            reader.Read();

            T constructedObject = objectConstructor.Invoke(reader);

            reader.Close();

            return constructedObject;
        }
        
        public string dateTimeToMySQLFormat(DateTime? dateTime) {
            return dateTime?.ToString("yyyy-MM-dd HH:mm:ss") ?? null;
        }
    }
}
