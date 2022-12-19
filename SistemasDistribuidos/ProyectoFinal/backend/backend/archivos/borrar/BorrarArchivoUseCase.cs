using backend._shared.expceptions;
using backend.archivos;
using backend.archivos._shared.espaciotrabajos;
using backend._shared;
using backend.archivos._comun.archivos;

namespace backend {
    public class BorrarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly ArchivoFinder archivoFinder;

        public BorrarArchivoUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            ArchivoFinder archivoFinder){
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.archivoFinder = archivoFinder;
        }

        public async void borrar(Guid archivoId, Guid usuarioId) {
            Archivo archivo = await this.archivoFinder.findByIdOrThrow(archivoId);
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(archivo.espacioTrabajoId, usuarioId);

            this.setArchivoBorrado(archivo, usuarioId);

            if (archivo.esCarpeta) {
                this.borrarSubArchivos(archivo, usuarioId);
            }
        }

        private async void borrarSubArchivos(Archivo archivoPadre, Guid usuarioId) {
            Queue<Archivo> archivosPendientesBorrar = new Queue<Archivo>(await this.findChildren(archivoPadre));;

            while(archivosPendientesBorrar.Count > 0) {
                Archivo archivoABorrar = archivosPendientesBorrar.Dequeue();

                this.setArchivoBorrado(archivoABorrar, usuarioId);

                if (archivoABorrar.esCarpeta) {
                    archivosPendientesBorrar = archivosPendientesBorrar.Concat(await this.findChildren(archivoABorrar)).ToQueue();
                }
            }
        }

        private async Task<List<Archivo>> findChildren(Archivo archivo) {
            return await this.archivosRepository.findChildrenByParentId(archivo.archivoId, archivo.espacioTrabajoId, false);
        }

        private void setArchivoBorrado(Archivo archivo, Guid usuarioId) {
            archivo.borrado = true;
            archivo.fechaBorrado = DateTime.Now;
            archivo.usuarioIdBorrado = usuarioId;

            this.archivosRepository.save(archivo);
        }
    }
}
