using backend._shared.expceptions;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.nuevacarpeta;

namespace backend.archivos {
    public class NuevaCarpetaUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;

        public NuevaCarpetaUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
        }

        public Archivo nuevacarpeta(NuevaCarpetaRequest request, Guid usuarioId) {
            this.ensureOwnsEspacioTrabajo(request.espacioTrabajoId, usuarioId);
            this.ensureArchivoPadreExists(request);
            this.ensureOtherCarpetasHasNoSameName(request);

            Archivo archivoCarpeta = new Archivo(
                archivoId: Guid.NewGuid(),
                espacioTrabajoId: request.espacioTrabajoId,
                borrado: false,
                fechaCreacion: DateTime.Now,
                fechaBorrado: null,
                usuarioIdBorrado: Guid.Empty,
                archivoPadreId: request.archivoPadreId,
                esCarpeta: true,
                nombre: request.nombreCarpeta!,
                formato: ""
           );

           this.archivosRepository.save(archivoCarpeta);

           return archivoCarpeta;
        }

        private void ensureOtherCarpetasHasNoSameName(NuevaCarpetaRequest request) {
            List<Archivo> contentPlaceToAddCarpeta = this.getContents(request);

            bool exsits = contentPlaceToAddCarpeta.Where(it => it.esCarpeta && it.nombre.Equals(request.nombreCarpeta)).Count() > 0;

            if (exsits) {
                throw new AlreadyExists("Ya existe una carpeta con el mismo nombre");
            }
        }

        private List<Archivo> getContents(NuevaCarpetaRequest request) {
            return request.archivoPadreId != Guid.Empty ?
                this.archivosRepository.findChildrenByParentId(request.archivoPadreId, request.espacioTrabajoId, false) :
                this.archivosRepository.findRootByEspacioTrabajoId(request.espacioTrabajoId, false);
        }

        private void ensureArchivoPadreExists(NuevaCarpetaRequest request) {
            if (request.archivoPadreId == Guid.Empty)
                return;

            Archivo archivo = this.archivosRepository.findById(request.archivoPadreId, request.espacioTrabajoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("No se ha encontrado el archivo de trabajo para crear la carpeta");
            }
        }

        private void ensureOwnsEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            if (this.espacioTrabajoPermisosService.puedeEscribir(espacioTrabajoId, usuarioId) == false) {
                throw new NotTheOwner("Ese espacio de trabajo no existe / No tienes permisos");
            }
        }
    }
}
