import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class Change {
    public static void changefile(String args) {
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
    }
}
