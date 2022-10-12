import java.io.File;
import java.util.Objects;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        int i = 0;
        File dir = new File("C:\\Users\\Anastasia\\Desktop\\parallel programing\\Lab1\\src");
        String[] javaFiles = new String[(dir.listFiles()).length];
        getFileExtentions check = new getFileExtentions();
        for(final File file : dir.listFiles()){
            if(Objects.equals(getFileExtentions.getFileEx(String.valueOf(file)), "java")){
                javaFiles[i] = file.getName();
                System.out.println(file.getName());
                file.renameTo( new File(dir, file.getName().replace(".java", ".txt")));
                Change.changefile(file.getAbsolutePath().replace(".java", ".txt"));
                System.out.println("__________________________________________________");
                i = i+1;
            }
        }
        for(final File file: dir.listFiles()){
            file.renameTo( new File(dir, file.getName().replace(".txt", ".java")));
        }

    }
}
