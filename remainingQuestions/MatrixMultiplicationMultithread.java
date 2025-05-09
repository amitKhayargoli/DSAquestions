package remainingQuestions;

public class MatrixMultiplicationMultithread {
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {

        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        if (n != B.length) {
            throw new IllegalArgumentException("Column count of A doesnt match with B rows");
        }

        Thread[] threads = new Thread[m];
        int[][] C = new int[m][p];

        for (int i = 0; i < m; i++) {
            final int row = i;

            threads[i] = new Thread(() -> {
                for (int j = 0; j < p; j++) {
                    for (int k = 0; k < n; k++) {

                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < m; i++) {
                threads[i].join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return C;
    }

    public static void main(String[] args) {
        int[][] A = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int[][] B = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };

        int[][] result = multiplyMatrices(A, B);

        System.out.println("Resultant Matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
