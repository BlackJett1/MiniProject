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

    private void displayMenu(){
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void searchTitles(){

    }

    private void searchCast(){

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

    private  void showMovieDetails(Movie movie) {
        System.out.println("Title: "+ movie.getMovie());
        System.out.println("Cast: "+ String.join(", ", movie.getCast()));
        System.out.println("Director: "+ movie.getDirector());
        System.out.println("OverView: "+ movie.getOverview());
        System.out.println("Runtime: "+movie.getRuntime()+ " minutes");
        System.out.println("User Rating: "+ movie.getUserRating());


    }
}
