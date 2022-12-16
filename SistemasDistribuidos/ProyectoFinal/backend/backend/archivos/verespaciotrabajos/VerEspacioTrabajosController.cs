using Microsoft.AspNetCore.Mvc;
using backend.archivos;
using Microsoft.AspNetCore.Authorization;

namespace backend.archivos {
    [ApiController]
    [Route("espaciotrabajos/ver")]
    [Authorize]
    public class VerEspacioTrabajosController : ControllerBase {
        private readonly VerEspacioTrabajosUseCase verEspacioTrabajosUseCase;

        public VerEspacioTrabajosController(VerEspacioTrabajosUseCase verEspacioTrabajosUseCase) {
            this.verEspacioTrabajosUseCase = verEspacioTrabajosUseCase;
        }

        [HttpGet]
        public List<EspacioTrabajo> ver() {
            return this.verEspacioTrabajosUseCase.ver(Guid.NewGuid());
        }
    }
}
