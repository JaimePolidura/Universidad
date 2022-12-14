namespace backend.usuarios.login {
    public class LoginResponse {
        public Guid usuarioId { get; }
        public string token { get; }
        
        public LoginResponse(Guid usuarioId, string token) {
            this.usuarioId = usuarioId;
            this.token = token;
        }
    }
}
