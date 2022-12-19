using backend._shared.expceptions;
using backend.archivos;
using backend.archivos._shared.espaciotrabajos;
using backend._shared;

namespace backend {
    public class BorrarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;

        public BorrarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
        }

        public async void borrar(Guid archivoId, Guid usuarioId) {
            Archivo archivo = await this.ensureArchivoExists(archivoId);
            this.ensureHasPermissionsInEspacioTrabajo(archivo.espacioTrabajoId, usuarioId);

            this.setArchivoBorradAndSave(archivo, usuarioId);

            if (archivo.esCarpeta) {
                this.borrarSubArchivos(archivo, usuarioId);
            }
        }

        private async void borrarSubArchivos(Archivo archivoPadre, Guid usuarioId) {
            Queue<Archivo> archivosPendientesBorrar = new Queue<Archivo>(await this.findChildren(archivoPadre));;

            while(archivosPendientesBorrar.Count > 0) {
                Archivo archivoABorrar = archivosPendientesBorrar.Dequeue();

                this.setArchivoBorradAndSave(archivoABorrar, usuarioId);

                if (archivoABorrar.esCarpeta) {
                    archivosPendientesBorrar = archivosPendientesBorrar.Concat(await this.findChildren(archivoABorrar)).ToQueue();
                }
            }
        }

        private async Task<List<Archivo>> findChildren(Archivo archivo) {
            return await this.archivosRepository.findChildrenByParentId(archivo.archivoId, archivo.espacioTrabajoId, false);
        }

        private void setArchivoBorradAndSave(Archivo archivo, Guid usuarioId) {
            archivo.borrado = true;
            archivo.fechaBorrado = DateTime.Now;
            archivo.usuarioIdBorrado = usuarioId;

            this.archivosRepository.save(archivo);
        }

        private async void ensureHasPermissionsInEspacioTrabajo(Guid espacioTrabajoId, Guid usuarioId) {
            if (! await this.espacioTrabajoPermisosService.puedeEscribir(espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("No tienes permisos");
            }
        }

        private async Task<Archivo> ensureArchivoExists(Guid archivoId) {
            Archivo archivo = await this.archivosRepository.findById(archivoId, false);

            if (archivo == null) {
                throw new ResourceNotFound("Archivo no encontrado");
            }

            return archivo;
        }
    }
}
