namespace backend._shared {
    public static class EnumerableExtensions {
        public static Queue<T> ToQueue<T>(this IEnumerable<T> source) {
            return new Queue<T>(source);
        }
    }
}
