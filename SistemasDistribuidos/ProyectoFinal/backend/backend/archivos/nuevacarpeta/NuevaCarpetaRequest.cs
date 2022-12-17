namespace backend.archivos.nuevacarpeta {
    public class NuevaCarpetaRequest {
        public Guid espacioTrabajoId { get; set;  }
        public Guid archivoPadreId { get; set;  }
        public string? nombreCarpeta { get; set; }
    }
}
