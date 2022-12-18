using backend._shared;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend {
    [ApiController]
    [Route("archivos/borrar")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class BorrarArchivoController : ApplicationController {
        private readonly BorrarArchivoUseCase borrarArchivoUseCase;

        public BorrarArchivoController(BorrarArchivoUseCase borrarArchivoUseCase) {
            this.borrarArchivoUseCase = borrarArchivoUseCase;
        }

        [HttpDelete]
        public void borrar([FromQuery] Guid archivoId) {
            this.borrarArchivoUseCase.borrar(archivoId, getLoggedUserId());
        }
    }
}
