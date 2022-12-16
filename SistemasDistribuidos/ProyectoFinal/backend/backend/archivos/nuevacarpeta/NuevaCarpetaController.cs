using backend._shared;
using backend.archivos.nuevacarpeta;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/nuevacarpeta")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class NuevaCarpetaController : ApplicationController {
        private readonly NuevaCarpetaUseCase nuevaCarpetaUsecase;

        public NuevaCarpetaController(NuevaCarpetaUseCase nuevaCarpetaUsecase) {
            this.nuevaCarpetaUsecase = nuevaCarpetaUsecase;
        }

        [HttpPost]
        public Archivo nuevaCarpeta(NuevaCarpetaRequest request) {
            return this.nuevaCarpetaUsecase.nuevacarpeta(request, getLoggedUserId());
        }
    }
}
