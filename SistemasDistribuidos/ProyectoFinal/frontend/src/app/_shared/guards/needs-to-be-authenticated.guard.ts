import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {UsuariosService} from "../../../api/users/usuarios.service";

@Injectable({
  providedIn: 'root'
})
export class NeedsToBeAuthenticatedGuard implements CanActivate {
  constructor(
    private usersService: UsuariosService,
    private router: Router,
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const isAuthenticated = this.usersService.isAuthenticated();

    if(!isAuthenticated)
      this.router.navigateByUrl("/login");

    return isAuthenticated;
  }

}
