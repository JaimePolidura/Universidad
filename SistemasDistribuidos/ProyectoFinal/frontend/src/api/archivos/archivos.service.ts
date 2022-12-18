import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendRoutesService} from "../backend-routes.service";
import {Observable} from "rxjs";
import {Archivo} from "./archivo";
import {NuevaCarpetaRequest} from "./api/nueva-carpeta-request";
import {RenombrarArchivoRequest} from "./api/renombrar-archivo-request";

@Injectable({
  providedIn: 'root'
})
export class ArchivosService {
  constructor(
    private httpClient: HttpClient,
    private backendRoutes: BackendRoutesService,
  ) {}

  public getArchivos(espacioTrabajoId: string, archivoPadreId?: string): Observable<Archivo[]> {
      return this.httpClient.get<Archivo[]>(`${this.backendRoutes.USING}/archivos/ver?espacioTrabajoId=${espacioTrabajoId}
          ${archivoPadreId == null ? '' : '&archivoPadreId=' + archivoPadreId}`);
  }

  public reemplazarArchivo(file: File, archivoId: string): Observable<Archivo> {
    const formData = new FormData();
    formData.append("blob", file);
    formData.append("archivoId", archivoId);

    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/reemplazararchivo`, formData);
  }

  public renombrar(req: RenombrarArchivoRequest): Observable<Archivo> {
    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/renombrar`, req);
  }

  public subirNuevoArchivo(file: File, espacioTrabajoId: string, archivoPadreId?: string): Observable<Archivo> {
    const formData = new FormData();
    formData.append("blob", file);
    formData.append("espacioTrabajoId", espacioTrabajoId);
    if(archivoPadreId != undefined) formData.append("archivoPadreId", archivoPadreId);

    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/nuevoarchivo`, formData);
  }

  public nuevaCarpeta(req: NuevaCarpetaRequest): Observable<Archivo> {
    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/nuevacarpeta`, req);
  }
}
