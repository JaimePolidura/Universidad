using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using System.Net;

namespace backend._shared.expceptions {

    [ApiController]
    [Route("[controller]")]
    public class ExceptionInterceptorController : ControllerBase {
        public IActionResult handleError() {
            var exceptionHandlerFeature = HttpContext.Features.Get<IExceptionHandlerFeature>();
            Exception expcetion = exceptionHandlerFeature!.Error;
              
            return expcetion switch {
                ResourceNotFound rnf  => NotFound(rnf.Message),
                NotTheOwner nto => Forbid(nto.Message),
                AlreadyExists ae => BadRequest(ae.Message),
                NotImplemented ni => StatusCode(501, ni.Message),
                IllegalState ist => BadRequest(ist.Message),
                _ => StatusCode(500, expcetion.Message)
            };
        }
    }
}
