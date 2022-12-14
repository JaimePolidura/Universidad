namespace backend.usuarios._shared {
    public class AuthenticationTokenService {
        public bool isValid(string token) {
            return true;
        }

        public string generate(Guid userId) {
            return "token";
        }

        public Guid getUserIdFromToken(string token) {
            return Startup.USUARIO_ID_DEFAULT;
        }
    }
}
