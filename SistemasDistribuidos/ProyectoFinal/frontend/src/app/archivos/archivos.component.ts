import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ArchivosService} from "../../api/archivos/archivos.service";
import {BehaviorSubject, map, Subject} from "rxjs";
import {Archivo} from "../../api/archivos/archivo";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {InputNameModalComponent} from "../_shared/components/input-name-modal/input-name-modal.component";
import {ToastrService} from "ngx-toastr";
import {
  ArchivoRutaNavegacionComponent,
  ItemRutaNavegacion
} from "./archivo-ruta-navegacion/archivo-ruta-navegacion.component";
import {BlobsService} from "../../api/blobs/blobs.service";
import {archivoComparator} from "./archivo/archivo-comparator";
import {ConfirmationModalComponent} from "../_shared/components/confirmation-modal/confirmation-modal.component";
import {NuevoNombreSeleccionado} from "./archivo/archivo.component";

@Component({
  selector: 'app-archivos',
  templateUrl: './archivos.component.html',
  styleUrls: ['./archivos.component.css']
})
export class ArchivosComponent implements OnInit {
  //@ts-ignore
  @ViewChild('rutaNavegacionComponent') rutaNavegacionComponent: ArchivoRutaNavegacionComponent;

  $loading = new BehaviorSubject<boolean>(true);
  archivos: Archivo[] = [];
  espacioTrabajoId: string = '';

  $nuevoArchivoCreado = new Subject<Archivo>();
  $archivosInictiales = new Subject<Archivo[]>();
  $nuevaCarpetaEntrada = new Subject<ItemRutaNavegacion>();
  $onArchivoReemplazado = new Subject<Archivo>();

  constructor(
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private archivosService: ArchivosService,
    private blobsSercice: BlobsService,
    private toast: ToastrService,
  ) { }

  ngOnInit(): void {
    this.espacioTrabajoId = this.route.snapshot.paramMap.get("espaciotrabajoid") as string;
    this.archivosService.getArchivos(this.espacioTrabajoId)
      .pipe(map(res => res.sort(archivoComparator)))
      .subscribe(archivos => {
        this.archivos = archivos;
        this.$archivosInictiales.next(archivos);
        this.$loading.next(false);
      });
  }

  nuevaCarpeta(): void {
    const modal = this.modalService.open(InputNameModalComponent);
    modal.componentInstance.name = 'nombre nueva carpeta';
    modal.componentInstance.nameSelected.subscribe((nombreCarpeta: string): void => {
      this.$loading.next(true);

      this.archivosService.nuevaCarpeta({
        nombreCarpeta: nombreCarpeta,
        espacioTrabajoId: this.espacioTrabajoId,
        archivoPadreId: this.rutaNavegacionComponent.getArchivoPadreId(),
      }).subscribe(nuevoArchivoCarpeta => {
        this.onNuevoArchivo(nuevoArchivoCarpeta, 'nueva carpeta creada');
      }, err => {
        console.error(err);
        this.toast.error(err, '');
      })
    });
  }

  onRutaNavegadaHaciaTras(item: ItemRutaNavegacion): void {
    this.archivos = item.archivos;
  }

  onArchivoSubirClick($event: Event): void {
    ($event.target as any).value = '';
  }

  onArchivoSubirSeleccionado($event: Event): void {
    const file: File = ($event as any).target.files[0];
    if(file == undefined || file.size > 10 * 1024 * 1024 * 8){
      this.toast.error('No se pueden subir archivos que pesen mas de 10GB');
      return;
    }

    const existeArchivoConElMismoNombre: boolean = this.archivos.filter(it => it.nombre.toLowerCase() == file.name.toLowerCase()).length > 0;

    if(existeArchivoConElMismoNombre){
      this.mostrarModalReempleazar(file);
    }else{
      this.subirNuevoArchivo(file);
    }
  }

  private mostrarModalReempleazar(file: File) {
    const confirmationModal = this.modalService.open(ConfirmationModalComponent);
    confirmationModal.componentInstance.title = '¿Desea reemplazar el archivo?';
    confirmationModal.componentInstance.body = 'Ya existe un archivo con el mismo nombre';
    confirmationModal.componentInstance.okButtonText = '✓ Reemplazar ✓';
    confirmationModal.componentInstance.onAccept.subscribe(() => {
      const archivoAReemplazar = this.archivos.filter(it => it.nombre.toLowerCase() == file.name.toLowerCase())[0];

      this.reemplazarArchivo(file, archivoAReemplazar);
    });
  }

  onNuevoNombreSeleccionado($event: NuevoNombreSeleccionado): void {
    const archivoConMismoNombre = this.archivos.filter(it => it.nombre.toLowerCase() == $event.nuevoNombre.toLowerCase()).length > 0;
    if(archivoConMismoNombre){
      this.toast.error('Ya existe un archivo en el mismo nombre');
      return;
    }

    this.archivosService.renombrar({archivoId: $event.archivo.archivoId, nuevoNombre: $event.nuevoNombre}).subscribe(nuevoArchivo => {
      const archivoRenombrar = this.archivos.filter(it => it.archivoId == $event.archivo.archivoId)[0];
      archivoRenombrar.nombre = nuevoArchivo.nombre;
    });
  }

  public reemplazarArchivo(file: File, archivo: Archivo): void {
    this.$loading.next(true);

    this.archivosService.reemplazarArchivo(file, archivo.archivoId).subscribe(nuevoArchivoReemplazdo => {
      this.$loading.next(false);
      this.$onArchivoReemplazado.next(nuevoArchivoReemplazdo);
      this.toast.success('Archivo reemplazdo','');

      archivo.nombre = nuevoArchivoReemplazdo.nombre;
      archivo.formato = nuevoArchivoReemplazdo.formato;

    }, err => {
      this.toast.error(err);
      this.$loading.next(false);
    });
  }

  private subirNuevoArchivo(file: File) {
    this.$loading.next(true);
    this.archivosService.subirNuevoArchivo(file, this.espacioTrabajoId, this.rutaNavegacionComponent.getArchivoPadreId()).subscribe(nuevoArchivo => {
      this.onNuevoArchivo(nuevoArchivo);
    }, err => {
      this.toast.error(err);
      this.$loading.next(false);
    });
  }

  onCarpetaSeleccionada(carpetaSeleccionada: Archivo): void {
    this.$loading.next(true);

    this.archivosService.getArchivos(this.espacioTrabajoId, carpetaSeleccionada.archivoId).subscribe(archivosEnCarpeta => {
      this.archivos = archivosEnCarpeta;
      this.$loading.next(false);
      this.$nuevaCarpetaEntrada.next({
        archivoPadreId: carpetaSeleccionada.archivoId,
        nombre: carpetaSeleccionada.nombre,
        archivos: archivosEnCarpeta
      });
    });
  }

  private onNuevoArchivo(archivo: Archivo, message: string = 'Nuevo archivo creado'): void {
    this.archivos.push(archivo);
    this.archivos.sort(archivoComparator);
    this.$loading.next(false);
    this.$nuevoArchivoCreado.next(archivo);

    this.toast.success(message);
  }
}
