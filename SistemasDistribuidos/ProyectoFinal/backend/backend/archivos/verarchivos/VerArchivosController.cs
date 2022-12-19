using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using backend._shared;
using backend.archivos._comun.archivos;

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
        public async Task<List<ArchivoResponse>> Get([FromQuery] Guid archivoPadreId, [FromQuery] Guid espacioTrabajoId, [FromQuery] bool incluirBorrados = false) {
            return await this.verArchivosUseCase.verArchivos(archivoPadreId, espacioTrabajoId, getLoggedUserId(), incluirBorrados);
        }
    }
}
