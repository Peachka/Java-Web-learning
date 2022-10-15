import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * Лабораторна робота No0
 * Лісовська Анстасія
 * ІО-01
 * E = A + B + C + D*(MA*MD)
 * ML = SORT(MF*MG)
 * S = (O + P) * TRANS(MR * MT)
 */

class MainClass{
    protected static void main(String args[])
    {
        Semaphore sem = new Semaphore(1);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter matrices and vectors dimension: ");
        int n = scanner.nextInt();

        System.out.println("Lab 0 start\n");
        Data data = new Data(n);
        T1 t1 = new T1("T1", Thread.MAX_PRIORITY, data, n, sem);
        T2 t2 = new T2("T2", Thread.NORM_PRIORITY, data, n, sem);
        T3 t3 = new T3("T3", Thread.MIN_PRIORITY, data, n, sem);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lab 0 end\n");
    }
}
