using System.Net;
using System.Security.Principal;

namespace backend.usuarios._shared {
    public class HttpRequestTokenFilter : DelegatingHandler {
        private readonly AuthenticationTokenService authenticationTokenService;
        private readonly UsuariosRepository usuariosRepository;

        public HttpRequestTokenFilter(AuthenticationTokenService authenticationTokenService, UsuariosRepository usuarios) {
            this.authenticationTokenService = authenticationTokenService;
            this.usuariosRepository = usuarios;
        }

        protected override Task<HttpResponseMessage> SendAsync(HttpRequestMessage req, CancellationToken canTok) {
            IEnumerable<string> authzHeaders;
            if (!req.Headers.TryGetValues("Authorization", out authzHeaders) || authzHeaders.Count() > 1)
                return base.SendAsync(req, canTok);

            var bearerToken = authzHeaders.ElementAt(0);
            var token = bearerToken.StartsWith("Bearer ") ? bearerToken.Substring(7) : bearerToken;
            if (!this.authenticationTokenService.isValid(token))
                return base.SendAsync(req, canTok);

            Guid userIdFromToken = this.authenticationTokenService.getUserIdFromToken(token);
            if (this.usuariosRepository.findById(userIdFromToken) == null)
                return base.SendAsync(req, canTok);

            Thread.CurrentPrincipal = new PrincipalApplication(userIdFromToken);

            return Task<HttpResponseMessage>.Factory.StartNew(() => new HttpResponseMessage(HttpStatusCode.OK) { });
        }
    }

    internal class PrincipalApplication : IPrincipal {
        private Guid usuarioId;

        public PrincipalApplication(Guid usuarioId) {
            this.usuarioId = usuarioId;
        }

        public IIdentity? Identity => new IdentityApplication(this.usuarioId);

        public bool IsInRole(string role) {
            return true;
        }
    }

    internal class IdentityApplication : IIdentity {
        private Guid usuarioId;

        public IdentityApplication(Guid usuarioId) {
            this.usuarioId = usuarioId;
        }

        public string? AuthenticationType => "";

        public bool IsAuthenticated => true;

        public string? Name => this.usuarioId.ToString();
    }
}
