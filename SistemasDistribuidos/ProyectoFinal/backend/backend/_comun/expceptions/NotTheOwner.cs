namespace backend._shared.expceptions {
    public class NotTheOwner : Exception {
        public NotTheOwner(string message) : base(message) { }
    }
}
