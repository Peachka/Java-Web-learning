import java.util.Random;
import java.util.concurrent.Semaphore;

protected class T3 extends Thread {

    int[] O, P;
    int[][] MR, MT;
    int n;
    protected int[] result;
    protected Data d;
    Semaphore sem;

    T3(String name, int priority, Data d, int n, Semaphore sem) {
        super(name);
        this.sem = sem;
        setPriority(priority);
        this.d = d;
        this.n = n;
        this.O = new int[n];
        this.P = new int[n];
        this.MR = new int[n][n];
        this.MT = new int[n][n];

    }
    @Override
    protected void run(){
        System.out.printf("_______%s started_______\n", Thread.currentThread().getName());
        int max = 10;
        if (n > 4){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            for(int i = 0; i < n; i++){
                O[i] = new Random().nextInt(max);
                P[i] = new Random().nextInt(max);
                for (int j = 0; j < n; j++){
                    MR[i][j] = new Random().nextInt(max);
                    MT[i][j] = new Random().nextInt(max);
                }
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            try {
                System.out.println(Thread.currentThread().getName() + " wait for acquire");
                sem.acquire();
                System.out.println(Thread.currentThread().getName() + " acquire done");
                O = d.vectorInput(n, "O");
                P = d.vectorInput(n, "P");
                MR = d.matrixInput(n, "MR");
                MT = d.matrixInput(n, "MT");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sem.release();
            System.out.println( Thread.currentThread().getName() + " released" );
        }

        result = d.F3( O, P ,MR, MT);
        System.out.println("Result T3");
        printVec(result);
        System.out.printf("_______%s ended_______\n", Thread.currentThread().getName());
    }

    static void printVec(int[] vec)
    {
        System.out.print("[");
        for (int i = 0; i < vec.length; i++) {
            System.out.print(vec[i] + " ");
        }
        System.out.print("]\n");
    }
}
