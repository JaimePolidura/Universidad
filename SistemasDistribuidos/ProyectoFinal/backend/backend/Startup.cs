using backend.usuarios._shared;
using backend.archivos;
using backend._shared;

namespace backend {
    public class Startup {
        public static Guid USUARIO_ID_DEFAULT = Guid.NewGuid();

        public IServiceProvider serviceProvider { get; }

        public Startup(IServiceProvider serviceProvider) {
            this.serviceProvider = serviceProvider;
        }
               
        public async void run() {
            var mysqlService = this.serviceProvider.GetService<MySQLService>();
            await mysqlService.startConnection();

            mysqlService.sendCommand("DELETE FROM usuarios");
            mysqlService.sendCommand("DELETE FROM archivos");
            mysqlService.sendCommand("DELETE FROM blobs");
            mysqlService.sendCommand("DELETE FROM espaciotrabajos");

            var usuariosRepository = this.serviceProvider.GetService<UsuariosRepository>();
            usuariosRepository.save(new Usuario(USUARIO_ID_DEFAULT, "jaime", "123"));

            var espacioTrabajoRepositorio = this.serviceProvider.GetService<EspacioTrabajoRepositorio>();
            espacioTrabajoRepositorio.save(new EspacioTrabajo(Guid.NewGuid(), USUARIO_ID_DEFAULT, "Por defecto", DateTime.Now, false, null));
        }
    }
}
