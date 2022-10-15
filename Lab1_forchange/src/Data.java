import java.util.*;

protected class Data {
    private int n;
    protected Data(int n) {
        this.n = n;
    }

    protected int[] F1(int[] A,int[] B, int[] C, int[] D, int[][] MA, int[][] MD) {
        return multiplexMatrix(addVec(C, addVec(A,B)), mulVecMatrix(D ,mulMatrix(MA,MD)));
    }
    protected int[][] F2(int[][] MF, int[][] MG) {
        return sortMat(mulMatrix(MF,MG));
    }
    protected int[] F3(int[] O, int[] P, int[][] MR, int[][] MT) {
        return multiplexMatrix(addVec(O, P) , trans(mulMatrix(MR,MT)));
    }

    protected int[][] trans(int[][] ma) {
        int res[][] = new int[ma.length][ma.length];

        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma.length; j++)
                res[i][j] = ma[j][i];
        }
        return res;
    }

    protected int[][] sortMat(int mat[][])
    {

        int temp[] = new int[mat.length * mat.length];
        int k = 0;

        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat.length; j++)
                temp[k++] = mat[i][j];

        Arrays.sort(temp);

        k = 0;
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat.length; j++)
                mat[i][j] = temp[k++];

        return mat;
    }
    protected int[][] mulMatrix(int ma[][], int mb[][]) {
        int res[][] = new int[ma.length][ma.length];

        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma.length; j++)
                res[i][j] = ma[i][j] * mb[i][j];
        }
        return res;
    }

    protected  int[] multiplexMatrix(int[] v, int[][] m) {
        int[] resV = new int[m[0].length];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < m.length; j++) {
                resV[i] += v[j] * m[j][i];
            }
        }
        return resV;
    }

    protected int[][] mulVecMatrix(int a[], int ma[][]){
        int res[][] = new int[ma.length][ma.length];

        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma.length; j++)
                res[j][i] = ma[j][i] * a[i];
        }
        return res;
    }

    protected int[] addVec(int a[], int b[]){
        int c[] = new int[a.length];
        for (int i = 0; i < a.length; i++ ){
            c[i] = a[i]+b[i];
        }
        return c;
    }

    protected int[] vectorInput(int n, String name) {
        int[] vector = new int[n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n ; i++){
            System.out.printf("Input vector element #%d in vector %s %n", i, name);
            vector[i] = sc.nextInt();
        }
        return vector;
    }

    protected int[][] matrixInput(int n, String name) {
        int[][] matrix = new int[n][n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n ; i++){
            for (int j = 0; j < n ; j++){
                System.out.printf("Input matrix element #[%d][%d] in matrix %s%n", i, j, name);
                        matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }
}
