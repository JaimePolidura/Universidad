﻿using backend._shared.expceptions;
using backend.archivos._comun.archivos;
using backend.archivos._shared.espaciotrabajos;
using backend.archivos.nuevacarpeta;

namespace backend.archivos {
    public class NuevaCarpetaUseCase {
        private readonly EspacioTrabajoPermisosService espacioTrabajoPermisosService;
        private readonly ArchivoResponseCreator archivoResponseCreator;
        private readonly ArchivosRepository archivosRepository;
        private readonly ArchivoFinder archivoFinder;

        public NuevaCarpetaUseCase(EspacioTrabajoPermisosService espacioTrabajoPermisosService, ArchivosRepository archivosRepository, 
            ArchivoFinder archivoFinder, ArchivoResponseCreator archivoResponseCreator) {
            this.espacioTrabajoPermisosService = espacioTrabajoPermisosService;
            this.archivosRepository = archivosRepository;
            this.archivoFinder = archivoFinder;
            this.archivoResponseCreator = archivoResponseCreator;
        }

        public async Task<ArchivoResponse> nuevacarpeta(NuevaCarpetaRequest request, Guid usuarioId) {
            this.espacioTrabajoPermisosService.puedeEsciribirOrThrow(request.espacioTrabajoId, usuarioId);
            if(request.archivoPadreId != Guid.Empty) await this.archivoFinder.findByIdOrThrow(request.archivoPadreId); //No se crea en el root
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
                nombreCarpeta: request.nombreCarpeta
           );

           this.archivosRepository.save(archivoCarpeta);

           return await this.archivoResponseCreator.createFromArchivo(archivoCarpeta);
        }

        private async void ensureOtherCarpetasHasNoSameName(NuevaCarpetaRequest request) {
            List<Archivo> contentPlaceToAddCarpeta = await this.getContents(request);

            bool exsits = contentPlaceToAddCarpeta.Where(it => it.esCarpeta && it.nombreCarpeta.Equals(request.nombreCarpeta)).Count() > 0;

            if (exsits) {
                throw new AlreadyExists("Ya existe una carpeta con el mismo nombre");
            }
        }

        private async Task<List<Archivo>> getContents(NuevaCarpetaRequest request) {
            return request.archivoPadreId != Guid.Empty ?
                await this.archivosRepository.findChildrenByParentId(request.archivoPadreId, request.espacioTrabajoId, false) :
                await this.archivosRepository.findRootByEspacioTrabajoId(request.espacioTrabajoId, false);
        }
    }
}
