namespace backend._shared.expceptions {
    public class ResourceNotFound : Exception {
        public ResourceNotFound(string message): base(message) { }
    }
}
