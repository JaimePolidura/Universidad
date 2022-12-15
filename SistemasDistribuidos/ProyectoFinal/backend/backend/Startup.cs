using backend.usuarios._shared;
using backend.archivos;

namespace backend {
    public class Startup {
        public static Guid USUARIO_ID_DEFAULT = Guid.NewGuid();

        public IServiceProvider serviceProvider { get; }

        public Startup(IServiceProvider serviceProvider) {
            this.serviceProvider = serviceProvider;
        }
                
        public void run() {
            var usuariosRepository = this.serviceProvider.GetService<UsuariosRepository>();
            usuariosRepository.save(new Usuario(USUARIO_ID_DEFAULT, "jaime", "123"));

            var espacioTrabajoRepositorio = this.serviceProvider.GetService<EspacioTrabajoRepositorio>();
            espacioTrabajoRepositorio.save(new EspacioTrabajo(Guid.NewGuid(), USUARIO_ID_DEFAULT, "Por defecto", DateTime.Now, false, null));
        }
    }
}
