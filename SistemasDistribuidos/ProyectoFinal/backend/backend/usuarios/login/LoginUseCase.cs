using backend._shared.expceptions;
using backend.usuarios._shared;

namespace backend.usuarios.login {
    public class LoginUseCase {
        private AuthenticationTokenService authenticationTokenService;
        private UsuariosRepository usuariosRepository;

        public LoginUseCase(UsuariosRepository usuariosRepository, AuthenticationTokenService authenticationTokenService) {
            this.usuariosRepository = usuariosRepository;
            this.authenticationTokenService = authenticationTokenService;
        }
        
        public LoginResponse login(LoginRequest request) {
            Usuario usuario = this.usuariosRepository.findByNombreAndClaves(request.nombre, request.claves);
            if (usuario == null)
                throw new ResourceNotFound("Usuario incorrecto");

            string token = this.authenticationTokenService.generate(usuario);
            
            return new LoginResponse(usuario.usuarioId, token);
        }
    }
}
