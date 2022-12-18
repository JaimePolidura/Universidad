export interface Archivo {
  archivoId: string;
  espacioTrabajoId: string;
  borrado: boolean;
  fechaCreacion: string;
  fechaBorrado: string;
  usuarioIdBorrado: string;
  archivoPadreId: string;
  esCarpeta: boolean;
  nombre: string;
  formato: string;
}
