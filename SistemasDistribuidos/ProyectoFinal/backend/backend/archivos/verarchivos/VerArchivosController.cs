using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using backend._shared;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/ver")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class VerArchivosController : ApplicationController {
        private readonly VerArchivosUseCase verArchivosUseCase;
        
        public VerArchivosController(VerArchivosUseCase verArchivosUseCase) {
            this.verArchivosUseCase = verArchivosUseCase;
        }

        [HttpGet]
        public List<Archivo> Get([FromQuery] Guid archivoPadreId, [FromQuery] Guid espacioTrabajoId, [FromQuery] bool incluirBorrados = false) {
            return this.verArchivosUseCase.verArchivos(archivoPadreId, espacioTrabajoId, getLoggedUserId(), incluirBorrados);
        }
    }
}
