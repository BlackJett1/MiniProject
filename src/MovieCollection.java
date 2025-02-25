import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter part of title: ");
            String search =scanner.next().toLowerCase();

            ArrayList<Movie> matchMovies = new ArrayList<>();
            for(Movie movie : list) {
                if(movie.getMovie().toLowerCase().contains(search)) {
                    matchMovies.add(movie);
                }
            }
            if (matchMovies.isEmpty()){
                System.out.println("No matches found.");

            } else {
                matchMovies.sort(Comparator.comparing(Movie::getMovie));
                for (int i = 0; i < matchMovies.size();i ++) {
                    System.out.println((i +1)+". "+matchMovies.get(i).getMovie());

                }
                System.out.println("Enter the number of the movie to see more details: ");
                int choice = scanner.nextInt() -1;
                if(choice >= 0 && choice < matchMovies.size()){
                    showMovieDetails(matchMovies.get(choice));
                }
            }
    }

    private void searchCast(){

    }

    private void importData() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/movies_data.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    String movie = fields[0];
                    int runtime = Integer.parseInt(fields[1]);
                    String director = fields[2];
                    String[] cast = fields[3].split("\\|");
                    String overview = fields[4];
                    double userRating = Double.parseDouble(fields[5]);
                Movie movie1 = new Movie(movie, cast, director, overview, runtime, userRating);
                list.add(movie1);
            } else {
                    System.out.println("Skipped malformed line: "+ line);
                }
            }
            System.out.println("Data import completed. Number of movies: "+ list.size());
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric data. ");
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
