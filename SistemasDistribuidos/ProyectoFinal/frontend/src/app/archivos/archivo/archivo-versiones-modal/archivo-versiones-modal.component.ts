import {Component, Input, OnInit} from '@angular/core';
import {Archivo} from "../../../../api/archivos/archivo";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {BehaviorSubject, Subject} from "rxjs";
import {ArchivoVersion} from "../../../../api/archivos/archivo-version";
import {ArchivosService} from "../../../../api/archivos/archivos.service";
import {BlobsService} from "../../../../api/blobs/blobs.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-archivo-versiones-modal',
  templateUrl: './archivo-versiones-modal.component.html',
  styleUrls: ['./archivo-versiones-modal.component.css']
})
export class ArchivoVersionesModalComponent implements OnInit {
  @Input() archivo: Archivo | undefined;

  $loading = new BehaviorSubject<boolean>(true);
  verionesArchivo: ArchivoVersion[] = [];

  constructor(
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

  download(version: ArchivoVersion): void {
    this.$loading.next(true);

    this.blobService.downloadBlob(this.archivo as Archivo, version.blobId).subscribe(
        () => {},
        err => {this.toast.error(err);},
      () => this.$loading.next(false));
  }
}
