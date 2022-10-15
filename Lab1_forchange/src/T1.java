import java.util.Random;
import java.util.concurrent.Semaphore;

protected class T1 extends Thread {

    int[] A, B, C, D;
    int[][] MA, MD;
    int n;
    protected int[] result;
    protected Data d;
    Semaphore sem;

    T1(String name, int priority, Data d, int n, Semaphore sem) {
        super(name);
        this.sem = sem;
        setPriority(priority);
        this.d = d;
        this.n = n;
        this.A = new int[n];
        this.B = new int[n];
        this.C = new int[n];
        this.D = new int[n];
        this.MA = new int[n][n];
        this.MD = new int[n][n];

    }
    @Override
    protected void run(){
        System.out.printf("_______%s started_______\n", Thread.currentThread().getName());
        int max = 10;
        if (n > 4) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            for (int i = 0; i < n; i++) {
                A[i] = new Random().nextInt(max);
                B[i] = new Random().nextInt(max);
                C[i] = new Random().nextInt(max);
                D[i] = new Random().nextInt(max);
                for (int j = 0; j < n; j++) {
                    MA[i][j] = new Random().nextInt(max);
                    MD[i][j] = new Random().nextInt(max);
                }
            }
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            try {
                System.out.println(Thread.currentThread().getName() + " wait for acquire");
                sem.acquire();
                System.out.println(Thread.currentThread().getName() + " acquire done");
                A = d.vectorInput(n, "A");
                B = d.vectorInput(n, "B");
                C = d.vectorInput(n, "C");
                D = d.vectorInput(n, "D");
                MA = d.matrixInput(n, "MA");
                MD = d.matrixInput(n, "MD");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}
            sem.release();
            System.out.println( Thread.currentThread().getName() + " released" );
        }

        result = d.F1(A, B, C, D, MA, MD);
        System.out.println("Result T1");
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

