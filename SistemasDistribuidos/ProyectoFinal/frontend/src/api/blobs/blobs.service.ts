import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendRoutesService} from "../backend-routes.service";
import {map, Observable} from "rxjs";
import {Archivo} from "../archivos/archivo";
import {saveAs} from "file-saver";

@Injectable({
  providedIn: 'root'
})
export class BlobsService {

  constructor(
    private httpClient: HttpClient,
    private backendRoutes: BackendRoutesService,
  ) { }

  public downloadBlob(archivo: Archivo, blobId: string): Observable<void> {
    return new Observable<void>(subscriber => {
      this.httpClient.get(`${this.backendRoutes.USING}/archivos/descargar?archivoId=${archivo.archivoId}&blobId=${blobId}&ultimaVersion=false`,{responseType: 'blob'})
        .pipe(map(res => <Blob> res))
        .subscribe(binary => {
          saveAs(binary, archivo.nombre);
          subscriber.complete();
        }, err => {
          subscriber.error(err);
          subscriber.complete();
        })
    });
  }

  public downloadLastVersion(archivo: Archivo): Observable<void> {
    return new Observable<void>(subscriber => {
      this.httpClient.get(`${this.backendRoutes.USING}/archivos/descargar?archivoId=${archivo.archivoId}`, { responseType: 'blob' })
        .pipe(map(res => <Blob> res))
        .subscribe(binary => {
          saveAs(binary, archivo.nombre);
          subscriber.complete();
          }, err => {
          subscriber.error(err);
        })
    });
  }
}
