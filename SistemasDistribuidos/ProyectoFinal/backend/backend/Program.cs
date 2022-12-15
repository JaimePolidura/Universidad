using backend;
using backend._shared.expceptions;
using backend.archivos;
using backend.usuarios._shared;
using backend.usuarios.login;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using Microsoft.Extensions.DependencyInjection;

var builder = WebApplication.CreateBuilder(args);
// Add services to the container.

builder.Services.AddControllers();
builder.Services.AddSingleton<LoginUseCase>();
builder.Services.AddSingleton<AuthenticationTokenService>();
builder.Services.AddSingleton<UsuariosRepository, InMemoryUsuariosRepository>();
builder.Services.AddSingleton<HttpRequestTokenFilter>();
builder.Services.AddSingleton<ExceptionInterceptorController>();
builder.Services.AddSingleton<HttpRequestTokenFilter>();

builder.Services.AddSingleton<ArchivosRepository, InMemoryArhivosRepository>();
builder.Services.AddSingleton<VerArchivosUseCase>();

builder.Services.AddSingleton<EspacioTrabajoRepositorio, InMemoryEspacioTrabajoRepositorio>();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
{
    options.RequireHttpsMetadata = false;
    options.SaveToken = true;
    options.TokenValidationParameters = new TokenValidationParameters()
    {
        ValidateIssuer = true,
        ValidateAudience = true,
        ValidAudience = builder.Configuration["Jwt:Audience"],
        ValidIssuer = builder.Configuration["Jwt:Issuer"],
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
    };
});

var app = builder.Build();

app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();
app.UseExceptionHandler("/ExceptionInterceptor");

var startup = new Startup(app.Services);
startup.run();

app.Run();