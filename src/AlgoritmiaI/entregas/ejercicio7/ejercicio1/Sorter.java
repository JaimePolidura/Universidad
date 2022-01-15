package AlgoritmiaI.entregas.ejercicio7.ejercicio1;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Sorter {
    public static void main(String[] args) throws IOException {
        removeDuplicatesAndSort(Paths.get("src/AlgoritmiaI/entregas/ejercicio7/ejercicio1/numeros1.txt").toFile());
    }

    public static void removeDuplicatesAndSort(File file) throws IOException {
        List<Integer> rawData =             readAndConvertToInts(file);
        List<Integer> removedDuplicates =   removeDuplcates(rawData);
        List<Integer> sorted =              sort(removedDuplicates);

        writeIn(new File("src/AlgoritmiaI/entregas/ejercicio7/ejercicio1/resultado.txt"), sorted);
    }

    private static List<Integer> readAndConvertToInts(File file) throws IOException {
        List<Integer> toReturn = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            for(String line; (line = reader.readLine()) != null; ) {
                toReturn.add(Integer.parseInt(line));
            }
        }

        return toReturn;
    }

    private static List<Integer> removeDuplcates(List<Integer> rawData) {
        return new ArrayList<>(new HashSet<>(rawData));
    }

    private static List<Integer> sort(List<Integer> removedDuplicates) {
        return removedDuplicates.stream()
                .sorted(Integer::compare)
                .collect(Collectors.toList());
    }

    private static void writeIn(File file, List<Integer> toWrite) throws IOException {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(file))){
            for (Integer value : toWrite) {
                out.write(value + "\n");
            }
        }
    }
}