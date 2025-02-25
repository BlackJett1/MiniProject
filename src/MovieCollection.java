import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Movie> list = new ArrayList<Movie>();

    public MovieCollection() {
        list = new ArrayList<>();
        importData();
        displayMenu();
    }

    private void importData() {
        try (BufferedReader br = new BufferedReader(new FileReader("movies_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String movie = fields[0];
                int runtime = Integer.parseInt(fields[1]);
                String director = fields[2];
                String[] cast = fields[3].split("\\|");
                String overview = fields[4];
                double userRating = Double.parseDouble(fields[5]);
                Movie movie1 = new Movie(movie, cast, director, overview, runtime, userRating);
                list.add(movie1);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
    }
}
