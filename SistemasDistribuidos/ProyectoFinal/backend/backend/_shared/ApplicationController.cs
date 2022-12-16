using Microsoft.AspNetCore.Mvc;

namespace backend._shared {
    public class ApplicationController : ControllerBase{
        public Guid getUserId() {
            return Guid.NewGuid();
        }
    }
}
