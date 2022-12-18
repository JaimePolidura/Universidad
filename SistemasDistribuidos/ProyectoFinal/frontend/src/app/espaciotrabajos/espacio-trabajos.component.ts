import { Component, OnInit } from '@angular/core';
import {UsuariosService} from "../../api/users/usuarios.service";
import {EspacioTrabajosService} from "../../api/espaciotrabajo/espacio-trabajos.service";
import {BehaviorSubject} from "rxjs";
import {EspacioTrabajo} from "../../api/espaciotrabajo/espacio-trabajo";
import {Router} from "@angular/router";

@Component({
  selector: 'app-espaciotrabajos',
  templateUrl: './espacio-trabajos.component.html',
  styleUrls: ['./espacio-trabajos.component.css']
})
export class EspacioTrabajosComponent implements OnInit {
  public $loading = new BehaviorSubject<boolean>(false);
  public espacioTrabajosLoaded: EspacioTrabajo[] = [];

  constructor(
    private espacioTrabajos: EspacioTrabajosService,
    private usersService: UsuariosService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.espacioTrabajos.getEspacioTrabajos().subscribe(res => {
      this.espacioTrabajosLoaded = res;
      this.$loading.next(false);
    })
  }

  onEspacioTrabajoSelecionado(espacioTrabajo: EspacioTrabajo): void {
    this.router.navigate(["archivos", espacioTrabajo.espacioTrabajoId]);
  }

}
