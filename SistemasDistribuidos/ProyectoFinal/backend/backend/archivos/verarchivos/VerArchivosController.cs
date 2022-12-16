using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authentication.JwtBearer;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/ver")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class VerArchivosController : ControllerBase {
        private readonly VerArchivosUseCase verArchivosUseCase;
        
        public VerArchivosController(VerArchivosUseCase verArchivosUseCase) {
            this.verArchivosUseCase = verArchivosUseCase;
        }

        [HttpGet]
        public List<Archivo> Get([FromQuery] Guid archivoPadreId, [FromQuery] Guid espacioTrabajoId) {
            var usuario = User;
            var porro = Thread.CurrentPrincipal;
            var joder = HttpContext.User;

            return this.verArchivosUseCase.verArchivos(archivoPadreId, espacioTrabajoId, Guid.NewGuid());
        }
    }
}
