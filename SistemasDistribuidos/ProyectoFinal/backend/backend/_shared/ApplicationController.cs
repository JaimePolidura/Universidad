using Microsoft.AspNetCore.Mvc;

namespace backend._shared {
    public class ApplicationController : ControllerBase{
        protected Guid getLoggedUserId() {
            return Guid.Parse(User.Identity.Name);
        }
    }
}
