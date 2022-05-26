package AlgoritmiaII.entregas.ejercicio7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SearchEngine {
    private final Map<String, Document> documentMap;

    public SearchEngine() {
        this.documentMap = new HashMap<>();
    }

    public boolean cacheUrl(String url) {
        if (documentMap.containsKey(url)) return false;

        this.documentMap.put(url, new Document(url, HtmlUtils.getContentFromUrl(url,10000)));

        return true;
    }

    public List<String> search(List<String> terms) {
        List<SearchIDFCalculation> searchIDFCalculations = new ArrayList<>();

        for(var entry : this.documentMap.entrySet()){
            searchIDFCalculations.add(new SearchIDFCalculation(
                    entry.getKey(),
                    calculateIDFInDocumentByTerms(entry.getKey(), terms)
            ));
        }

        return searchIDFCalculations.stream()
                .sorted(((o1, o2) -> (int) (o1.idf-o2.idf)))
                .map(SearchIDFCalculation::url)
                .toList();
    }

    private double calculateIDFInDocumentByTerms(String key, List<String> terms) {
        return terms.stream()
                .mapToDouble(this::getTermIdf)
                .sum();
    }

    record SearchIDFCalculation(String url, double idf){}

    public double getTermIdf(String term) {
        return documentMap.keySet().stream()
                .map(documentMap::get)
                .mapToDouble(document -> Math.log((double) documentMap.size() /(double) document.getTf(term)) + 1)
                .sum();
    }
}
