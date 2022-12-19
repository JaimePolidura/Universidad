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
        public async Task<ActionResult> descargar([FromQuery] Guid archivoId, [FromQuery] Guid blobId, [FromQuery] bool ultimaVersion = true) {
            Blob blob = await this.descargarArchivoUseCase.descargar(archivoId, getLoggedUserId(), blobId, ultimaVersion);
               
            return File(blob.binario, blob.formato, fileDownloadName: blob.nombre);
        }
    }
}
