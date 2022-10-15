import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class Change implements Runnable{

    private final String args;

    public Change(String args){
        this.args = args;
    }

    public void changefile () {

        //System.out.printf("  %s thread started with file %s\n", Thread.currentThread().getName(), args);
        File file = new File(args);
        file.renameTo(new File(args, file.getName().replace(".java", ".txt")));


        BufferedReader buffreader;
        BufferedWriter buffwrite;
        try {
            buffwrite = new BufferedWriter(new FileWriter( args, true));
            buffreader = new BufferedReader(new FileReader( args));
            String line = buffreader.readLine();
            PrintWriter writer = new PrintWriter(args);
            writer.print("");
            writer.close();
            while (line != null) {
                if (line.contains("public")) {
                    line = line.replace("public", "protected");
                    buffwrite.write(line+"\n");
                }
                else {
                    buffwrite.write(line+"\n");
                }
                line = buffreader.readLine();
            }
            buffwrite.close();
            buffreader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.renameTo(new File(args, file.getName().replace(".txt", ".java")));

        //System.out.printf("  %s thread ended with file %s\n", Thread.currentThread().getName(), args);
        System.out.printf("  %s thread ended with file %s\n", Thread.currentThread().getName(), new File(args).getName());
    }


    @Override
    public void run() {
        changefile();
    }
}

