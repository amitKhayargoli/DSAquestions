import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MatrixMultiplier implements Runnable {
    private final int[][] A, B, result;
    private final int row, col, size;

    public MatrixMultiplier(int[][] A, int[][] B, int[][] result, int row, int col, int size) {
        this.A = A;
        this.B = B;
        this.result = result;
        this.row = row;
        this.col = col;
        this.size = size;
    }

    @Override
    public void run() {
        for (int k = 0; k < size; k++) {
            result[row][col] += A[row][k] * B[k][col];
        }
    }
}

class MultiThreadedMatrixMultiplication {
    public static void main(String[] args) {
        int N = 3; // Matrix size (NxN)
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
        int[][] result = new int[N][N];

        // Using thread pool for efficient execution
        ExecutorService executor = Executors.newFixedThreadPool(N * N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                executor.execute(new MatrixMultiplier(A, B, result, i, j, N));
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        // Display Result
        System.out.println("Resultant Matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
