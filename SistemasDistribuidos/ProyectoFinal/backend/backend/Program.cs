using backend;
using backend._shared.expceptions;
using backend.archivos;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.verarchivos;
using backend.espaciotrabajos;
using backend.usuarios._shared;
using backend.usuarios.login;

var builder = WebApplication.CreateBuilder(args);
// Add services to the container.

builder.Services.AddControllers();
builder.Services.AddSingleton<LoginUseCase>();
builder.Services.AddSingleton<AuthenticationTokenService>();
builder.Services.AddSingleton<UsuariosRepository, InMemoryUsuariosRepository>();
builder.Services.AddSingleton<HttpRequestTokenFilter>();
builder.Services.AddSingleton<ExceptionInterceptorController>();

builder.Services.AddSingleton<ArchivosRepository, InMemoryArhivosRepository>();
builder.Services.AddSingleton<VerArchivosUseCase>();

builder.Services.AddSingleton<EspacioTrabajoRepositorio, InMemoryEspacioTrabajoRepositorio>();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.UseExceptionHandler("/ExceptionInterceptor");

var startup = new Startup(app.Services);
startup.run();

app.Run();
