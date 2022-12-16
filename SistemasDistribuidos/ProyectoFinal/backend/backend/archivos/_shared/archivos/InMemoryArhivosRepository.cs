using System.Collections;

namespace backend.archivos
{
    public class InMemoryArhivosRepository : ArchivosRepository {
        private List<Archivo> archivos = new List<Archivo>();

        public void save(Archivo archivo) {
            int index = this.archivos.FindIndex(it => it.archivoId.Equals(archivo.archivoId));

            if (index != -1)
                this.archivos.RemoveAt(index);
               
            this.archivos.Add(archivo);
        }
          
        public Archivo findById(Guid archivoId, Guid espacioTrabajoId) {
            return this.archivos.Where(archivo => archivo.archivoId.Equals(archivoId) && 
                        archivo.espacioTrabajoId.Equals(espacioTrabajoId)).First();
        }

        public List<Archivo> findChildrenByParentId(Guid archivoId, Guid espacioTrabajoId, bool verBorrados) {
            return this.archivos.Where(archivo => archivo.archivoPadreId.Equals(archivoId) 
                        && archivo.espacioTrabajoId.Equals(espacioTrabajoId))
                .Where(it => verBorrados || !it.borrado)
                .ToList();
        }

        public List<Archivo> findRootByEspacioTrabajoId(Guid espacioTrabajoId, bool verBorrados) {
            return this.archivos.Where(archivo => archivo.espacioTrabajoId.Equals(espacioTrabajoId)
                    && (archivo.archivoPadreId == Guid.Empty))
                .Where(it => verBorrados || !it.borrado)
                .ToList();
        }

        public void deleteById(Guid archivoId, Guid espacioTrabajoId) {
            this.archivos.RemoveAll(it => it.archivoId.Equals(archivoId) 
                && it.espacioTrabajoId.Equals(espacioTrabajoId));
        }
    }
}
