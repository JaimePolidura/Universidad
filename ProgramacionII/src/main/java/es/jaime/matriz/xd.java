package es.jaime.matriz;

public final class xd {
    public static void main(String[] args) {
        int n = 5;
        int[][] mat = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                mat[i][j] = (int) (Math.random() * 10);
            }
        }

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }

            System.out.print("\n");
        }

        int[][] suma = new int[n][n];
        System.out.println("    ");

        for(int i = 0; i < (2 * n - 4); i++){
            
        }
    }
}
