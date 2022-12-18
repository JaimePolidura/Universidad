using backend;
using backend._shared.expceptions;
using backend.archivos;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.reemplazararchivo;
using backend.usuarios._shared;
using backend.usuarios.login;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args);
// Add services to the container.

//Comun
builder.Services.AddControllers();
builder.Services.AddSingleton<ExceptionInterceptorController>();
builder.Services.AddSingleton<HttpRequestTokenFilter>();

//Usuarios
builder.Services.AddSingleton<AuthenticationTokenService>();
builder.Services.AddSingleton<UsuariosRepository, InMemoryUsuariosRepository>();

//EspacioTrabajos
builder.Services.AddSingleton<EspacioTrabajoRepositorio, InMemoryEspacioTrabajoRepositorio>();
builder.Services.AddSingleton<VerEspacioTrabajosUseCase>();
builder.Services.AddSingleton<EspacioTrabajoPermisosService>();

//Archivos
builder.Services.AddSingleton<ArchivosRepository, InMemoryArhivosRepository>();

//Blobs
builder.Services.AddSingleton<BlobRepository, InMemoryBlobRepository>();

//Casos de uso
builder.Services.AddSingleton<VerArchivosUseCase>();
builder.Services.AddSingleton<NuevaCarpetaUseCase>();
builder.Services.AddSingleton<DescargarArchivoUseCase>();
builder.Services.AddSingleton<LoginUseCase>();
builder.Services.AddSingleton<SubirNuevoArchivoUseCase>();
builder.Services.AddSingleton<ReemplazarArchivoUseCase>();

builder.Services.AddCors(cors => cors.AddPolicy("AllowAll", builder => builder.AllowAnyHeader().AllowAnyOrigin().AllowAnyMethod()));
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
{
    options.TokenValidationParameters = new TokenValidationParameters()
    {
        ValidateIssuer = true,
        ValidateAudience = true,
        ValidateIssuerSigningKey = true,
        ValidAudience = builder.Configuration["Jwt:Audience"],
        ValidIssuer = builder.Configuration["Jwt:Issuer"],
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
    };
});

var app = builder.Build();

app.UseCors("AllowAll");
app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();
app.UseExceptionHandler("/ExceptionInterceptor");

var startup = new Startup(app.Services);
startup.run();

app.Run();