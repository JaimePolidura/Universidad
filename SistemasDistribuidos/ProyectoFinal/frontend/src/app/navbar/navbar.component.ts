import { Component} from '@angular/core';
import {UsuariosService} from "../../api/users/usuarios.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(
    private usuarioService: UsuariosService,
    private router: Router,
  ) { }

  onTitleClicked(): void {
    this.router.navigateByUrl(this.usuarioService.isAuthenticated() ? '/espaciotrabajos' : '/login');
  }
}
