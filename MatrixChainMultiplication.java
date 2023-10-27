public class MatrixChainMultiplication {
    
    // Function to multiply two matrices
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
    
    // Matrix Chain Multiplication - Dynamic Programming Approach
    public static int matrixChainOrder(int[] dims) {
        int n = dims.length;
        int[][] m = new int[n][n];
        
        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }
        
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int cost = m[i][k] + m[k + 1][j] + dims[i - 1] * dims[k] * dims[j];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }
        return m[1][n - 1];
    }

    public static void main(String[] args) {
        // Test matrix multiplication
        System.out.println("Multiplied Matrix Result");
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        
        int[][] B = {
            {2, 0},
            {3, 4}
        };
        
        int[][] result = multiplyMatrices(A, B);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        
        // Test matrix chain multiplication
        int[] dims = {1, 2, 3, 4, 3};
        System.out.println("\nMinimum number of multiplications: " + matrixChainOrder(dims));
    }
}
