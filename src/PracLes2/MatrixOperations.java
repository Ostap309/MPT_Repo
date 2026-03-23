package PracLes2;

public class MatrixOperations {

    static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.printf("%4s", elem);
            }
            System.out.println();
        }
    }

    static int[][] transpose(int[][] matrix) {
        int length = matrix.length;
        int rows_n = matrix.length;
        int columns_n = matrix[0].length;
        var matrixT = new int[columns_n][rows_n];

        for (short i = 0; i < rows_n; i++) {
            for (short j = 0; j < columns_n; j++) {
                matrixT[j][i] = matrix[i][j];
            }
        }

        return matrixT;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int joint_length = a[0].length;
        if (joint_length != b.length) {
            System.out.println("ОШИБКА РАЗМЕРНОСТИ!");
            return null;
        }

        var new_matrix = new int[a.length][b[0].length];
        int matrix_elem;

        for (short i = 0; i < a.length; i++) {
            for (short j = 0; j < b[0].length; j++) {
                matrix_elem = 0;
                for (short n = 0; n < joint_length; n++) {
                    matrix_elem += a[i][n] * b[n][j];
                }
                new_matrix[i][j] = matrix_elem;
            }
        }
        return new_matrix;
    }

    static int diagonalSum(int[][] matrix) {
        int sum = 0;
        for (short i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] b = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        System.out.println("Матрица A (2x3):");
        print(a);

        System.out.println("\nТранспонированная A (3x2):");
        print(transpose(a));

        System.out.println("\nМатрица B (3x2):");
        print(b);

        int[][] c = multiply(a, b);
        System.out.println("\nA * B (2x2):");
        print(c);

        System.out.println("\nСумма диагонали A*B: " + diagonalSum(c));
    }
}

