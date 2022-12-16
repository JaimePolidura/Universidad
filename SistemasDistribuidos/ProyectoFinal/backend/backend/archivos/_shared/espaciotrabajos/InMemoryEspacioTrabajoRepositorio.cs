﻿using backend.archivos;

namespace backend.archivos {
    public class InMemoryEspacioTrabajoRepositorio : EspacioTrabajoRepositorio {
        private readonly List<EspacioTrabajo> espacioTrabajos = new List<EspacioTrabajo>();

        public void save(EspacioTrabajo espacioTrabajo) {
            int index = this.espacioTrabajos.FindIndex(item => item.espacioTrabajoId.Equals(espacioTrabajo.espacioTrabajoId));

            if (index != -1)
                this.espacioTrabajos.RemoveAt(index);

            this.espacioTrabajos.Add(espacioTrabajo);
        }

        public EspacioTrabajo findById(Guid espacioTrabajoId) {
            return this.espacioTrabajos.Where(it => it.espacioTrabajoId.Equals(espacioTrabajoId)).FirstOrDefault();
        }
          
        public List<EspacioTrabajo> findByUsuarioId(Guid usuarioId, bool incluirBorrados) {
            return this.espacioTrabajos.Where(it => it.usuarioId.Equals(usuarioId))
                .Where(it => incluirBorrados || !it.borrado)
                .ToList();
        }
    }
}