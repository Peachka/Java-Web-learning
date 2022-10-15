import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Main {

    public static ExecutorService executor;

    public static void recursive(String direct) {
        File dir = new File(direct);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                recursive(file.getAbsolutePath());
            }
            if (Objects.equals(getFileExtentions.getFileEx(String.valueOf(file)), "java")) {
                //System.out.println("New task " + file.getAbsolutePath());
                executor.execute(new Change(file.getAbsolutePath()));

            }
        }
    }

    public static void main(String[] args){

        String direct;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть дирторію, наприклад \\C:\\Users\\parallel programing\\Lab1\\:");
        direct = sc.nextLine();

        while (true){
            if(new File(direct).isDirectory() ){
                System.out.println("You are here " + direct);
                break;
            }
            else {
                System.out.println("You writed " + direct);
                System.out.println("Wrong directory input, try again:");
                direct = sc.nextLine();
            }
        }

        int n_threads;
        while (true) {
            System.out.print("nThreads from 4 to 20 recommended: ");
            n_threads = sc.nextInt();
            if (n_threads > 0 && n_threads <= 20)
                break;
            System.out.println("Try again!");
        }
        executor = Executors.newFixedThreadPool(n_threads);
        recursive(direct);

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ignored) {
            System.out.println("\nExecutor terminated.");
        }
    }
}


