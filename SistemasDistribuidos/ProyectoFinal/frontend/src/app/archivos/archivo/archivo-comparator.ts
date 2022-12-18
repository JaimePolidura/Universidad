import {Archivo} from "../../../api/archivos/archivo";

export function archivoComparator(a: Archivo, b: Archivo): number {
  return a.esCarpeta ? 1 : (b.esCarpeta ? 1 : 0);
}
