namespace backend._shared {
    public class BytesReader {
        public static byte[] readFromFormFile(IFormFile formFile) {
            using (var ms = new MemoryStream()) {
                formFile.CopyTo(ms);
                return ms.ToArray();
            }
        }
    }
}
