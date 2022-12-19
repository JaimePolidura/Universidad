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
        public async Task<LoginResponse> login(LoginRequest request) {
            return await this.loginUseCase.login(request); ;
        }
    }
}
