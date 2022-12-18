using backend._shared;
using backend.archivos._shared.blobs;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.archivos {
    [ApiController]
    [Route("archivos/verversiones")]
    [Authorize]
    public class VerVersionesController : ApplicationController {
        private readonly VerVersionesUseCase verVersionesUseCase;

        public VerVersionesController(VerVersionesUseCase verVersionesUseCase) {
            this.verVersionesUseCase = verVersionesUseCase;
        }

        [HttpGet]
        public List<VersionArchivoBlob> verVersiones([FromQuery] Guid archivoId) {
            return this.verVersionesUseCase.ver(archivoId, getLoggedUserId());
        }
    }
}
