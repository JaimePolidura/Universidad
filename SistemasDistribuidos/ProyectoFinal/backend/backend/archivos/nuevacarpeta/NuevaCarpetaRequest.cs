namespace backend.archivos.nuevacarpeta {
    public class NuevaCarpetaRequest {
        public Guid espacioTrabajoId { get; }
        public Guid archivoPadreId { get; }
        public string? nombreCarpeta { get; }
    }
}
