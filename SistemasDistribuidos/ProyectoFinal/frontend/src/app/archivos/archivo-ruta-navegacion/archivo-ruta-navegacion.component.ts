import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output
} from '@angular/core';
import {Archivo} from "../../../api/archivos/archivo";
import {Subject} from "rxjs";
import {NgbCalendarHijri} from "@ng-bootstrap/ng-bootstrap/datepicker/hijri/ngb-calendar-hijri";

@Component({
  selector: 'app-archivo-ruta-navegacion',
  templateUrl: './archivo-ruta-navegacion.component.html',
  styleUrls: ['./archivo-ruta-navegacion.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ArchivoRutaNavegacionComponent implements OnInit {
  @Input() $archivosInictiales: Subject<Archivo[]> | undefined;
  @Input() $onNuevoArchivoCreado: Subject<Archivo> | undefined;
  @Input() $onNuevaCarpetaEntrada: Subject<ItemRutaNavegacion> | undefined;
  @Input() $onArchivoReemplazado: Subject<Archivo> | undefined;

  @Output() rutaNavegada = new EventEmitter<ItemRutaNavegacion>();

  rutaActual: ItemRutaNavegacion[] = [];

  constructor(private changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.$archivosInictiales?.subscribe(archivosIniciales => this.onArchivosInicialesLoaded(archivosIniciales));
    this.$onNuevoArchivoCreado?.subscribe(nuevaCarpeta => this.onNuevaArchivoCreado(nuevaCarpeta));
    this.$onNuevaCarpetaEntrada?.subscribe(nuevaRutaItem => this.onNuevaCarpetaEntrada(nuevaRutaItem));
    this.$onArchivoReemplazado?.subscribe(archivoReemplazdado => this.onArchivoReemplazado(archivoReemplazdado))
  }

  private onArchivoReemplazado(nuevoArchivoReemplazdo: Archivo): void {
    const archivoReemplazdo = this.getArchivosActuales().filter(it => it.archivoId == nuevoArchivoReemplazdo.archivoId)[0];

    archivoReemplazdo.nombre = nuevoArchivoReemplazdo.nombre;
    archivoReemplazdo.formato = nuevoArchivoReemplazdo.nombre;

    this.changeDetectorRef.detectChanges();
  }

  private onArchivosInicialesLoaded(archivosIniciales: Archivo[]): void {
    this.rutaActual.push({nombre: 'Inicio', archivos: [...archivosIniciales]});
  }

  private onNuevaArchivoCreado(nuevoArchivo: Archivo): void {
    this.rutaActual[this.rutaActual.length - 1].archivos = [
      ...this.getArchivosActuales(),
      nuevoArchivo,
    ];
  }

  private onNuevaCarpetaEntrada(nuevaRutaItem: ItemRutaNavegacion): void {
    this.rutaActual.push(nuevaRutaItem);
    this.changeDetectorRef.detectChanges();
  }

  public navegarHacia(itemRuta: ItemRutaNavegacion, index: number): void {
    this.rutaActual.splice(index + 1, this.rutaActual.length - (index + 1));
    this.rutaNavegada.next(this.rutaActual[this.rutaActual.length - 1]);
  }

  public getArchivosActuales(): Archivo[] {
    return [...this.rutaActual[this.rutaActual.length - 1].archivos];
  }

  public getArchivoPadreId(): string | undefined {
    return this.rutaActual[this.rutaActual.length - 1].archivoPadreId;
  }
}

export interface ItemRutaNavegacion {
  archivoPadreId?: string;
  nombre: string;
  archivos: Archivo[];
}
