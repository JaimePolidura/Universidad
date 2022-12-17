using backend._shared.expceptions;
using backend.archivos._shared.blobs;
using backend.archivos._shared.espaciotrabajos;
using Microsoft.AspNetCore.Mvc;
using System.Reflection.Metadata;

namespace backend.archivos {
    public class DescargarArchivoUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivosRepository archivosRepository;
        private readonly BlobRepository blobRepository;

        public DescargarArchivoUseCase (EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
                BlobRepository blobRepository) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.blobRepository = blobRepository;
        }

        public byte[] descargar(Guid archivoId, Guid usuarioId, int version = -1, bool ultimaVersion = true) {
            if (!ultimaVersion) throw new NotImplemented("Funcionalidadm no implementada");
            Archivo archivo = this.ensureArchivoExistsAndOwnsArchivo(archivoId, usuarioId);

            Blob blob = this.blobRepository.findByArchivoIdAndLastVersion(archivo.archivoId);

            return blob.binario;
        }

        private Archivo ensureArchivoExistsAndOwnsArchivo(Guid archivoId, Guid usuarioId) {
            Archivo archivo = this.archivosRepository.findById(archivoId, false);
            if (archivo == null) {
                throw new ResourceNotFound("Archivo no encontrado");
            }
            if(archivo.esCarpeta) {
                throw new IllegalState("No se pueden descargar carpetas");
            }
            if (!this.espacioTrabajoPermisosService.puedeLeer(archivo.espacioTrabajoId, usuarioId)) {
                throw new NotTheOwner("Este espacio trabajo no te corresponde");
            }

            return archivo;
        }
    }
}
