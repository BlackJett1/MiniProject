import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("src/movies_data.csv");
        if (!file.exists()) {
            System.out.println("File does not exist.");
        } else {
            System.out.println("File exists!");
        }

    }
}
