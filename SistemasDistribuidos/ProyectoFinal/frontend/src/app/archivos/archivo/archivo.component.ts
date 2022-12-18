import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Archivo} from "../../../api/archivos/archivo";
import {BlobsService} from "../../../api/blobs/blobs.service";
import {ToastrService} from "ngx-toastr";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {InputNameModalComponent} from "../../_shared/components/input-name-modal/input-name-modal.component";
import {ConfirmationModalComponent} from "../../_shared/components/confirmation-modal/confirmation-modal.component";
import {ConfirmControlButtonsStyles} from "../../_shared/components/confirmation-modal/confirm-control-buttons-styles";
import {NgbCalendarHijri} from "@ng-bootstrap/ng-bootstrap/datepicker/hijri/ngb-calendar-hijri";

@Component({
  selector: 'app-archivo',
  templateUrl: './archivo.component.html',
  styleUrls: ['./archivo.component.css']
})
export class ArchivoComponent {
  @Input() archivo: Archivo | undefined;

  @Output() carpetaSeleccionada = new EventEmitter<Archivo>();
  @Output() nuevoNombreSeleccionado = new EventEmitter<NuevoNombreSeleccionado>();
  @Output() archivoMarcadoParaBorrar = new EventEmitter<Archivo>();

  constructor(
    private blobService: BlobsService,
    private modalService: NgbModal,
    private toast: ToastrService,
  ) { }

  abrirModalRenombrar(): void {
    const modal = this.modalService.open(InputNameModalComponent);
    modal.componentInstance.name = 'nuevo nombre';
    modal.componentInstance.nameSelected.subscribe((nuevoNombre: string): void => {
      this.nuevoNombreSeleccionado.next({archivo: this.archivo as Archivo, nuevoNombre: nuevoNombre});
    });
  }

  abrirModalConfirmacionBorrar(): void {
    const modal = this.modalService.open(ConfirmationModalComponent);
    modal.componentInstance.title = `¿Seguro que quieres borrar ${this.archivo?.nombre}?`;
    modal.componentInstance.body = this.archivo?.esCarpeta ? 'Borrara todos los contenidos' : '';
    modal.componentInstance.styles = ConfirmControlButtonsStyles.CANCEL_RED_OK_GREEN;
    modal.componentInstance.okButtonText = '✓ Borrar ✓';
    modal.componentInstance.onAccept.subscribe(() => {
      this.archivoMarcadoParaBorrar.next(this.archivo as Archivo);
    });
  }

  archivoClickeado(): void {
    if(this.archivo?.esCarpeta)
      this.carpetaSeleccionada.next(this.archivo as Archivo);
    else
      this.descargar();
  }

  imageSrc(): string {
    return this.archivo?.esCarpeta ? 'assets/img/carpeta.png' : 'assets/img/archivo.png';
  }

  public descargar() {
    this.blobService.downloadLastVersion(this.archivo as Archivo).subscribe(result => {}, err => {
      this.toast.error('Error al descargar el archivo');
    });
  }
}

export interface NuevoNombreSeleccionado {
  nuevoNombre: string;
  archivo: Archivo;
}
