using backend._shared;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/descargar")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class DescargarArchivoController : ApplicationController {
        private readonly DescargarArchivoUseCase descargarArchivoUseCase;

        public DescargarArchivoController(DescargarArchivoUseCase descargarArchivoUseCase) {
            this.descargarArchivoUseCase = descargarArchivoUseCase;
        }

        [HttpGet]
        public byte[] descargar([FromQuery] Guid archivoId, [FromQuery] int version = -1, [FromQuery] bool ultimaVersion = true) {
            var binario = this.descargarArchivoUseCase.descargar(archivoId, getLoggedUserId(), version, ultimaVersion);

            return binario;
        }
    }
}
