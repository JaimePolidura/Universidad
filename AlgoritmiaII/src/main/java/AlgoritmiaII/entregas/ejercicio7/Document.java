package AlgoritmiaII.entregas.ejercicio7;

import java.util.*;
import java.util.stream.Collectors;

public class Document {
    private final String name;
    private final Map<String, Integer> frequencies;

    public Document(String name, String contents) {
        this.name = name;
        this.frequencies = getTermsFrequencies(contents);
    }

    private Map<String, Integer> getTermsFrequencies(String contents) {
        List<String> allTerms = Arrays.stream(contents.split(" "))
                .map(TextUtils::trimAndClean)
                .toList();

        return allTerms
                .stream()
                .distinct()
                .collect(Collectors.toMap(
                   term -> term,
                   term -> Collections.frequency( allTerms, term  )
                ));
    }

    public String getName() {
        return name;
    }

    public List<String> getTerms() {
            return new ArrayList<>(frequencies.keySet());
    }

    public int getNumOccurences(String term) {
        final String fixedTerm = TextUtils.trimAndClean(term);
        if(frequencies.containsKey(fixedTerm)){
            return frequencies.get(fixedTerm);
        }
        return 0;
    }

    public double getTf(String term) {
        final int termCount = getNumOccurences(term);
        if(termCount == 0) return 0;

        final int termsTotalCount = this.frequencies.values()
                .stream().mapToInt(Integer::valueOf)
                .sum();

        return (1d*termCount) / termsTotalCount;
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

}
