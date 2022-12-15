using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/ver")]
    [Authorize]
    public class VerArchivosController : ControllerBase {
        private readonly VerArchivosUseCase verArchivosUseCase;
        
        public VerArchivosController(VerArchivosUseCase verArchivosUseCase) {
            this.verArchivosUseCase = verArchivosUseCase;
        }

        [HttpGet]
        public List<Archivo> Get([FromQuery] Guid archivoPadreId, [FromQuery] Guid espacioTrabajoId) {
            return this.verArchivosUseCase.verArchivos(archivoPadreId, espacioTrabajoId, Guid.NewGuid());
        }
    }
}
