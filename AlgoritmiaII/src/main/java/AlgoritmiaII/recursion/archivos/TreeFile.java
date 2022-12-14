package AlgoritmiaII.recursion.archivos;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class TreeFile {
    public static void main(String[] args) {
        TreeFile archivos = new TreeFile();

        archivos.tree(".pdf", new File("./src/AlgoritmiaII/clase/recursion/archivos/root"));
    }

    public String tree(String regExp, File root){
        List<String> lines = new ArrayList<>();

        treeRecursive(regExp, root, lines, 0);

        for (String line : lines) {
            System.out.println(line);
        }

        return "null";
    }

    private void treeRecursive(String regExp, File root, List<String> lines, int depth){
        List<File> files = Arrays.stream(root.list())
                .map(o -> new File(buildAbsolutePath(root, o)))
                .sorted(this.orderByTypeFolder())
                .toList();

        for (File file : files) {
            if(file.isDirectory()){
                lines.add(getLineFromPath(file, ++depth));
                treeRecursive(regExp, file, lines, ++depth);
            }else{
                if (this.matchesRegExp(file.getName(),regExp))
                    lines.add(getLineFromPath(file, depth));
            }
        }
    }

    private boolean matchesRegExp(String text, String regExp){
        return regExp.equals("") || text.matches(regExp);
    }

    private String buildAbsolutePath(File file, String subFolderName){
        return String.format("%s%s%s",file.getPath(), File.separator,subFolderName);
    }

    private String getLineFromPath(File file, int depth){
        return String.format("%s|%s%s", getSpaces(depth), file.isDirectory() ? ">" : "+", file.getName());
    }

    private String getSpaces(int times){
        return " ".repeat(Math.max(0, times));
    }

    private Comparator<? super File> orderByTypeFolder(){
        return (o1, o2) -> o1.isDirectory() && !o2.isDirectory() ? 1 : !o1.isDirectory() && o2.isDirectory() ? -1 : 0;
    }
}

