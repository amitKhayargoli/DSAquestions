public class FloydWarshall {

    int floydwarshall(int[][] d) {

        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {

                for (int j = 0; i < d.length; j++) {

                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        // To detect negative edge cycle

        for (int i = 0; i < d.length; i++) {
            if (d[i][i] < 0) {
                return 1;
            }
        }

        return -1;
    }

}
