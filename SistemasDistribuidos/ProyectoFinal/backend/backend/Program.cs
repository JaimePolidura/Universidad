using backend;
using backend._shared.expceptions;
using backend.archivos;
using backend.usuarios._shared;
using backend.usuarios.login;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

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

builder.Services.AddSingleton<NuevaCarpetaUseCase>();

builder.Services.AddSingleton<EspacioTrabajoRepositorio, InMemoryEspacioTrabajoRepositorio>();
builder.Services.AddSingleton<VerEspacioTrabajosUseCase>();

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