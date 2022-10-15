import java.util.Random;
import java.util.concurrent.Semaphore;

protected class T2 extends Thread {

    int[][] MF, MG;
    int n;
    protected int[][] result;
    protected Data d;
    Semaphore sem;

    T2(String name, int priority, Data d, int n, Semaphore sem) {
        super(name);
        this.sem = sem;
        setPriority(priority);
        this.d = d;
        this.n = n;
        this.MF = new int[n][n];
        this.MG = new int[n][n];

    }
    @Override
    protected void run(){
        System.out.printf("_______%s started_______\n", Thread.currentThread().getName());
        int max = 10;
        if (n > 4){
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            for(int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    MF[i][j] = new Random().nextInt(max);
                    MG[i][j] = new Random().nextInt(max);
                }
            }
        }else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            try {
                System.out.println(Thread.currentThread().getName() + " wait for acquire");
                sem.acquire();
                System.out.println(Thread.currentThread().getName() + " acquire done");
                MF = d.matrixInput(n, "MF");
                MG = d.matrixInput(n, "MG");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sem.release();
            System.out.println( Thread.currentThread().getName() + " released" );

        }
        result = d.F2( MF, MG);
        System.out.println("Result T2");
        printMat(result);
        System.out.printf("_______%s ended_______\n", Thread.currentThread().getName());
    }

    static void printMat(int[][] mat)
    {
        System.out.print("[");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            if (i == mat.length -1){
                System.out.println("]");
            }
            else {
                System.out.println();
            }
        }
    }
}
