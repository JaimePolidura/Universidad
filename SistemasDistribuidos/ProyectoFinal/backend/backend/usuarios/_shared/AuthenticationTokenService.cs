using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace backend.usuarios._shared {
    public class AuthenticationTokenService {
        //TODO Mover a archivo de configuracion
        public static readonly string JWT_SECRET_KEY = "1062n761782tyb9867TB816201M2UM08Y";

        public IConfiguration _configuration;

        public AuthenticationTokenService(IConfiguration configuration) {
            _configuration = configuration;
        }

        public bool isValid(string token) {
            return true;
        }

        public string generate(Usuario usuario) {
            var claims = new[] {
                        new Claim(JwtRegisteredClaimNames.Sub, _configuration["Jwt:Subject"]),
                        new Claim(JwtRegisteredClaimNames.Jti, usuario.usuarioId.ToString()),
                        new Claim(JwtRegisteredClaimNames.Iat, DateTime.UtcNow.ToString()),
                        new Claim(JwtRegisteredClaimNames.Name, usuario.usuarioId.ToString()),
                        new Claim("UserId", usuario.usuarioId.ToString()),
                        new Claim("DisplayName", usuario.nombre),
                        new Claim("UserName", usuario.nombre),
            };

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Jwt:Key"]));
            var signIn = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
            var token = new JwtSecurityToken(
                issuer: _configuration["Jwt:Issuer"],
                audience: _configuration["Jwt:Audience"],
                claims,
                expires: DateTime.UtcNow.AddMinutes(10),
                signingCredentials: signIn);

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

        public Guid getUserIdFromToken(string token) {
            return Startup.USUARIO_ID_DEFAULT;
        }
    }
}
