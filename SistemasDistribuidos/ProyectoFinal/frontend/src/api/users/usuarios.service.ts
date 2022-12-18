import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendRoutesService} from "../backend-routes.service";
import {Observable, tap} from "rxjs";
import {Usuario} from "./model/usuario";
import {LoginRequest} from "./model/login-request";

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {
  private authenticatedUsuario: Usuario | undefined;

  constructor(
    private httpClient: HttpClient,
    private backend: BackendRoutesService,
  ) { }

  public login(req: LoginRequest): Observable<Usuario> {
    return this.httpClient.post<Usuario>(`${this.backend.USING}/usuarios/login`, req).pipe(
      tap(res => this.authenticatedUsuario = res)
    );
  }

  public isAuthenticated(): boolean {
    return this.authenticatedUsuario != undefined;
  }

  public getUsuario(): Usuario {
    return this.authenticatedUsuario as Usuario;
  }
}
