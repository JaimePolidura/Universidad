import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EspacioTrabajo} from "../../../api/espaciotrabajo/espacio-trabajo";

@Component({
  selector: 'app-espacio-trabajo',
  templateUrl: './espacio-trabajo.component.html',
  styleUrls: ['./espacio-trabajo.component.css']
})
export class EspacioTrabajoComponent {
  @Input() espacioTrabajo: EspacioTrabajo | undefined;

  @Output() espacioTrabajoSeleccionado = new EventEmitter<EspacioTrabajo>();

  onEspacioTrabajoSeleccionad() {
    this.espacioTrabajoSeleccionado.next(this.espacioTrabajo as EspacioTrabajo);
  }
}
