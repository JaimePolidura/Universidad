import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendRoutesService} from "../backend-routes.service";
import {Observable} from "rxjs";
import {Archivo} from "./archivo";
import {NuevaCarpetaRequest} from "./api/nueva-carpeta-request";
import {RenombrarArchivoRequest} from "./api/renombrar-archivo-request";
import {ArchivoVersion} from "./archivo-version";
import {archivoComparator} from "../../app/archivos/archivo/archivo-comparator";

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

  public getVersiones(archivoId: string): Observable<ArchivoVersion[]> {
    return this.httpClient.get<ArchivoVersion[]>(`${this.backendRoutes.USING}/archivos/verversiones?archivoId=${archivoId}`);
  }

  public borrar(archivoId: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.backendRoutes.USING}/archivos/borrar?archivoId=${archivoId}`);
  }

  public renombrar(req: RenombrarArchivoRequest): Observable<Archivo> {
    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/renombrar`, req);
  }

  public nuevaCarpeta(req: NuevaCarpetaRequest): Observable<Archivo> {
    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/nuevacarpeta`, req);
  }

  public reemplazarArchivo(file: File, archivoId: string): Observable<Archivo> {
    const formData = new FormData();
    formData.append("blob", file);
    formData.append("archivoId", archivoId);

    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/reemplazararchivo`, formData);
  }

  public subirNuevoArchivo(file: File, espacioTrabajoId: string, archivoPadreId?: string): Observable<Archivo> {
    const formData = new FormData();
    formData.append("blob", file);
    formData.append("espacioTrabajoId", espacioTrabajoId);
    if(archivoPadreId != undefined) formData.append("archivoPadreId", archivoPadreId);

    return this.httpClient.post<Archivo>(`${this.backendRoutes.USING}/archivos/nuevoarchivo`, formData);
  }
}
