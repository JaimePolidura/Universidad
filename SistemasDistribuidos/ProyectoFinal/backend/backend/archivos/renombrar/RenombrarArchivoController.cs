using backend._shared;
using backend.archivos._comun.archivos;
using backend.archivos.renombrar;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/renombrar")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class RenombrarArchivoController : ApplicationController {
        private readonly RenombrarArchivoUseCase renombrarArchivoUseCase;

        public RenombrarArchivoController(RenombrarArchivoUseCase renombrarArchivoUseCase) {
            this.renombrarArchivoUseCase = renombrarArchivoUseCase;
        }

        [HttpPost]
        public async Task<ArchivoResponse> renombrar(RenombrarArchivoRequest request) {
            return await this.renombrarArchivoUseCase.renombrar(request, getLoggedUserId());
        }
    }
}
