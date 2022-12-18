using backend._shared;
using backend.archivos.reemplazararchivo;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/reemplazararchivo")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class ReemplazarArchivoController : ApplicationController {
        private readonly ReemplazarArchivoUseCase reemplazarArchivoUseCase;

        public ReemplazarArchivoController(ReemplazarArchivoUseCase reemplazarArchivoUseCase) {
            this.reemplazarArchivoUseCase = reemplazarArchivoUseCase;
        }

        [HttpPost]
        public Archivo reemplazar([FromForm] ReemplazarArchivoRequest request) {
            return this.reemplazarArchivoUseCase.reemplazar(request, getLoggedUserId());
        }
    }
}
