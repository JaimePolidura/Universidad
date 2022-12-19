using backend._shared;
using backend.archivos._comun.archivos;
using backend.archivos.subirarchivo;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/nuevoarchivo")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class SubirNuevoArchivoControlador : ApplicationController {
        private readonly SubirNuevoArchivoUseCase subirNuevoArchivoUseCase;

        public SubirNuevoArchivoControlador(SubirNuevoArchivoUseCase subirNuevoArchivoUseCase) {
            this.subirNuevoArchivoUseCase = subirNuevoArchivoUseCase;
        }

        [HttpPost]
        public async Task<ArchivoResponse> subirNuevoArchivo([FromForm] SubirNuevoArchivoRequest request) {
            return await this.subirNuevoArchivoUseCase.subirNuevoArchivo(request, getLoggedUserId());
        }
    }
}
