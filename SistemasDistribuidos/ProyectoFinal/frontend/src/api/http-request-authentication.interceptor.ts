import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {UsuariosService} from "./users/usuarios.service";

const TOKEN_HEADER_KEY = 'Authorization';
const bypassUrl: string[] = [
  "/usuarios/login",
];

@Injectable()
export class HttpRequestAuthenticationInterceptor implements HttpInterceptor {

  constructor(private usuariosService: UsuariosService) {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.needsAuth(req))
      req = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.usuariosService.getUsuario().token) });

    return next.handle(req);
  }

  private needsAuth(request: HttpRequest<unknown>): boolean {
    return bypassUrl.filter(url => request.url.includes(url)).length == 0;
  }
}
