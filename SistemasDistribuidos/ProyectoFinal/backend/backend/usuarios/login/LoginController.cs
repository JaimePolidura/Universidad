using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.usuarios.login {
    [ApiController]
    [Route("usuarios/login")]
    [AllowAnonymous]
    public class LoginController : ControllerBase {
        private readonly LoginUseCase loginUseCase;

        public LoginController(LoginUseCase loginUseCase) {
            this.loginUseCase = loginUseCase;
        }

        [HttpPost]
        public LoginResponse login(LoginRequest request) {
            return this.loginUseCase.login(request); ;
        }
    }
}
