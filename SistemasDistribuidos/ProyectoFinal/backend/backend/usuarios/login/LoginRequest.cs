using System.Text.Json.Serialization;

namespace backend.usuarios.login {
    public class LoginRequest {
        public string nombre { get; set; }
        public string claves { get; set; }
    }
}
