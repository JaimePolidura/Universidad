import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Archivo} from "../../../api/archivos/archivo";
import {BlobsService} from "../../../api/blobs/blobs.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-archivo',
  templateUrl: './archivo.component.html',
  styleUrls: ['./archivo.component.css']
})
export class ArchivoComponent {
  @Input() archivo: Archivo | undefined;

  @Output() carpetaSeleccionada = new EventEmitter<Archivo>();

  constructor(
    private blobService: BlobsService,
    private toast: ToastrService,
  ) { }

  archivoClickeado(): void {
    if(this.archivo?.esCarpeta)
      this.carpetaSeleccionada.next(this.archivo as Archivo);
    else
      this.download();
  }

  imageSrc(): string {
    return this.archivo?.esCarpeta ? 'assets/img/carpeta.png' : 'assets/img/archivo.png';
  }

  private download() {
    //TODO Add spinner
    this.blobService.downloadLastVersion(this.archivo as Archivo).subscribe(result => {}, err => {
      this.toast.error('Error al descargar el archivo');
    });
  }
}
