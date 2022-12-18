import {Component, Input, OnInit} from '@angular/core';
import {Archivo} from "../../../../api/archivos/archivo";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BehaviorSubject, Subject} from "rxjs";
import {ArchivoVersion} from "../../../../api/archivos/archivo-version";
import {ArchivosService} from "../../../../api/archivos/archivos.service";
import {BlobsService} from "../../../../api/blobs/blobs.service";
import {ToastrService} from "ngx-toastr";
import {ConfirmationModalComponent} from "../../../_shared/components/confirmation-modal/confirmation-modal.component";

@Component({
  selector: 'app-archivo-versiones-modal',
  templateUrl: './archivo-versiones-modal.component.html',
  styleUrls: ['./archivo-versiones-modal.component.css']
})
export class ArchivoVersionesModalComponent implements OnInit {
  // @ts-ignore
  @Input() archivo: Archivo;

  $loading = new BehaviorSubject<boolean>(true);
  verionesArchivo: ArchivoVersion[] = [];

  constructor(
    private modalService: NgbModal,
    public activeModal: NgbActiveModal,
    private archivosService: ArchivosService,
    private blobService: BlobsService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.archivosService.getVersiones((<Archivo> this.archivo).archivoId).subscribe(veriones => {
      this.verionesArchivo = veriones; //Ya vienen ordenadas
      this.$loading.next(false);
    });
  }

  restaurar(version: ArchivoVersion, index: number): void {
    const confirmationModal = this.modalService.open(ConfirmationModalComponent);
    confirmationModal.componentInstance.title = '¿Seguro que quieres restuar los cambios?';
    confirmationModal.componentInstance.body = 'Se borraran todos los cambios que has hecho a parti de ' + version.fechaCreacion.split("T")[0];
    confirmationModal.componentInstance.onAccept.subscribe(() => {
      this.$loading.next(true);

      this.archivosService.restaurar({archivoId: (this.archivo as Archivo).archivoId, blobIdRestaurar: version.blobId}).subscribe(nuevaVersion => {
        this.$loading.next(false);
        this.toast.success("Se ha restaurado");

        this.archivo.nombre = nuevaVersion.nombre;
        this.archivo.formato = nuevaVersion.formato;
        this.verionesArchivo.splice(0, index);
      }, err => {
        this.toast.error(err);
        this.$loading.next(false);
      });
    });
  }

  download(version: ArchivoVersion): void {
    this.$loading.next(true);

    this.blobService.downloadBlob(this.archivo as Archivo, version.blobId).subscribe(
        () => {},
        err => {this.toast.error(err);},
      () => this.$loading.next(false));
  }
}
