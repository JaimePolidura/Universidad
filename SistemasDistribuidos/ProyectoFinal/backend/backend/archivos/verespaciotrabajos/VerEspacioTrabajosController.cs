using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;
using backend._shared;

namespace backend.archivos {
    [ApiController]
    [Route("espaciotrabajos/ver")]
    [Authorize]
    public class VerEspacioTrabajosController : ApplicationController {
        private readonly VerEspacioTrabajosUseCase verEspacioTrabajosUseCase;

        public VerEspacioTrabajosController(VerEspacioTrabajosUseCase verEspacioTrabajosUseCase) {
            this.verEspacioTrabajosUseCase = verEspacioTrabajosUseCase;
        }

        [HttpGet]
        public List<EspacioTrabajo> ver([FromQuery] bool incluirBorrados = false) {
            return this.verEspacioTrabajosUseCase.ver(getLoggedUserId(), incluirBorrados);
        }
    }
}
