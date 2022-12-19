using backend._shared;
using backend.archivos._comun.archivos;
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
        public async Task<ArchivoResponse> nuevaCarpeta(NuevaCarpetaRequest request) {
            return await this.nuevaCarpetaUsecase.nuevacarpeta(request, getLoggedUserId());
        }
    }
}
