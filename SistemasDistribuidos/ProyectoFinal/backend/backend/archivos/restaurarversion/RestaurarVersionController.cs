using backend._shared;
using backend.archivos._shared.blobs;
using backend.archivos.restaurarversion;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/restaurarversion")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class RestaurarVersionController : ApplicationController {
        private readonly RestaurarVersionUseCase restaurarVersionUseCase;

        public RestaurarVersionController(RestaurarVersionUseCase restaurarVersionUseCase) {
            this.restaurarVersionUseCase = restaurarVersionUseCase;
        }

        [HttpPost]
        public async Task<VersionArchivoBlob> restaurar(RestaurarVersionRequest request) {
            return await this.restaurarVersionUseCase.restaurar(request, getLoggedUserId());
        }
    }
}
