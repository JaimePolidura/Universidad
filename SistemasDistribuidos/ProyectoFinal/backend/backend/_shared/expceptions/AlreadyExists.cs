namespace backend._shared.expceptions {
    public class AlreadyExists : Exception {
        public AlreadyExists(string message) : base(message) { }
    }
}
