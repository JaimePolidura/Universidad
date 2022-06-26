package AlgoritmiaI.proyectofinal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Utils {
    private Utils() {}

    public static List<String> getLinesFrom(File file) throws IOException {
        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            for(String line; (line = reader.readLine()) != null; ) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static String removeCharacters(String text, char... charactersToRemove){
        String textReplaced = text;

        for (char characterToRemove : charactersToRemove) {
            textReplaced = text.replace(String.valueOf(characterToRemove), "");
        }

        return textReplaced;
    }
}
