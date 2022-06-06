package AlgoritmiaII.entregas.ejercicio7;

import java.util.*;

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
        List<SearchTFIDFCalculation> searchIDFCalculations = new ArrayList<>();

        for (var entry : this.documentMap.entrySet()) {
            searchIDFCalculations.add(new SearchTFIDFCalculation(
                    entry.getKey(),
                    getTermIdf(entry.getValue(), terms) * terms.stream().map(term -> entry.getValue().getTf(term)).mapToDouble(i -> i).sum()
            ));
        }

        return searchIDFCalculations.stream()
                .sorted((o1, o2) -> Double.compare(o2.tfidf, o1.tfidf))
                .map(SearchTFIDFCalculation::url)
                .toList();
    }

    private double calculateIDFInDocumentByTerms(String key, List<String> terms) {
        return terms.stream()
                .mapToDouble(this::getTermIdf)
                .sum();
    }

    record SearchTFIDFCalculation(String url, double tfidf){}

    public double getTermIdf(Document document, List<String> terms){
        return terms.stream()
                .mapToDouble(term -> Math.log((double) documentMap.size() / (numberOfOcurrencesInTerms(term, document))) + 1)
                .sum();
    }

    public double getTermIdf(String term) {
        return documentMap.keySet().stream()
                .map(documentMap::get)
                .mapToDouble(document -> Math.log((double) documentMap.size() / (numberOfOcurrencesInTerms(term))) + 1)
                .max()
                .getAsDouble();
    }

    private double numberOfOcurrencesInTerms(String term, Document document){
        double count = 0;
        List<String> terms = document.getTerms();

        count = numberOfOcurrencesInDoc(term, terms) > 0 ? count + 1 : count;

        return count;
    }

    private double numberOfOcurrencesInTerms(String term){
        double count = 0;

        for(Document document : this.documentMap.values()){
            List<String> terms = document.getTerms();

            count = numberOfOcurrencesInDoc(term, terms) > 0 ? count + 1 : count;
        }

        return count;
    }

    private double numberOfOcurrencesInDoc(String term, List<String> terms) {
        return terms.stream()
                .filter(term::equalsIgnoreCase)
                .count();
    }
}
