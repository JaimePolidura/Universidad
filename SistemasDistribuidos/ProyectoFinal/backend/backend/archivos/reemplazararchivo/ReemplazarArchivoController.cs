using backend._shared;
using backend.archivos._comun.archivos;
using backend.archivos.reemplazararchivo;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/reemplazar")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class ReemplazarArchivoController : ApplicationController {
        private readonly ReemplazarArchivoUseCase reemplazarArchivoUseCase;

        public ReemplazarArchivoController(ReemplazarArchivoUseCase reemplazarArchivoUseCase) {
            this.reemplazarArchivoUseCase = reemplazarArchivoUseCase;
        }

        [HttpPost]
        public async Task<ArchivoResponse> reemplazar([FromForm] ReemplazarArchivoRequest request) {
            return await this.reemplazarArchivoUseCase.reemplazar(request, getLoggedUserId());
        }
    }
}
