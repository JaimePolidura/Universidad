import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Archivo} from "../../../api/archivos/archivo";
import {BlobsService} from "../../../api/blobs/blobs.service";
import {ToastrService} from "ngx-toastr";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {InputNameModalComponent} from "../../_shared/components/input-name-modal/input-name-modal.component";

@Component({
  selector: 'app-archivo',
  templateUrl: './archivo.component.html',
  styleUrls: ['./archivo.component.css']
})
export class ArchivoComponent {
  @Input() archivo: Archivo | undefined;

  @Output() carpetaSeleccionada = new EventEmitter<Archivo>();
  @Output() nuevoNombreSeleccionado = new EventEmitter<NuevoNombreSeleccionado>();

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
