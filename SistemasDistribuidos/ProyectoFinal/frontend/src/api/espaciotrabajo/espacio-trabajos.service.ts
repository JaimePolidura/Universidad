import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendRoutesService} from "../backend-routes.service";
import {Observable, of, tap} from "rxjs";
import {EspacioTrabajo} from "./espacio-trabajo";

@Injectable({
  providedIn: 'root'
})
export class EspacioTrabajosService {
  private espacioTrabajos: EspacioTrabajo[] = [];

  constructor(
    private httpClient: HttpClient,
    private backendRoutes: BackendRoutesService,
  ) { }

  public getEspacioTrabajos(): Observable<EspacioTrabajo[]> {
    return this.espacioTrabajos.length == 0 ?
      this.httpClient.get<EspacioTrabajo[]>(`${this.backendRoutes.USING}/espaciotrabajos/ver`).pipe(
        tap(res => this.espacioTrabajos = res)
      ) :
      of(this.espacioTrabajos);
  }
}
