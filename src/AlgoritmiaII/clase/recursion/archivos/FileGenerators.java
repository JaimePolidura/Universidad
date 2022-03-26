package AlgoritmiaII.clase.recursion.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class FileGenerators {
    private final List<String> extensiones = List.of("pdf", "txt", "doc", "docx", "xlsx", "img");
    private final List<String> names;
    private final List<String> lastnames;

    public FileGenerators() throws Exception {
        this.names = this.getLines("./src/AlgoritmiaII/clase/recursion/archivos/names.txt");
        this.lastnames = this.getLines("./src/AlgoritmiaII/clase/recursion/archivos/lastnames.txt");
    }

    public void generate(){
        int depth = 5;
        File root = new File("./src/AlgoritmiaII/clase/recursion/archivos/generator");
        root.mkdir();

    }

    private void generateRecursive(int depth, File root){
        if (depth == 5) return;

        int filesToCreate = (int) (Math.random() * 5);

        for (int i = 0; i < filesToCreate; i++) {
            boolean isDirectory = Math.random() < 0.5;
        }
    }

    private String getRandomValue(List<String> list){
        return list.get((int) (Math.random() * list.size()));
    }

    private List<String> getLines(String path) throws Exception {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line = reader.readLine();

            while (line != null) {
                lines.add(reader.readLine());
            }
        }

        return lines;
    }
}
