package AlgoritmiaII.entregas.ejercicio7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;

public class HtmlUtils {
    public static String getContentFromUrl(String url, int timeout) {
        try {
            final org.jsoup.nodes.Document document = Jsoup.connect(url)
                    .timeout(timeout)
                    .get();

            final String html = document.html()
                    .replaceAll("[\\n]", "")
                    .replaceAll("(\\\\n)", "")
                    .replaceAll("[\s]{2}", "");

            return Jsoup.clean(
                    html,
                    "",
                    Whitelist.none(),
                    new Document.OutputSettings().prettyPrint(false));

        } catch (IOException e) {
            throw new RuntimeException("JSoup connect timeout reached");
        }
    }
}
