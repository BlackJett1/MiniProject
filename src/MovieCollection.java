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
        importData();  // Import data from the CSV file
        displayMenu();  // Start the menu
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
        String search = scanner.next().toLowerCase();

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
            for (int i = 0; i < matchMovies.size(); i++) {
                System.out.println((i + 1) + ". " + matchMovies.get(i).getMovie());
            }
            System.out.println("Enter the number of the movie to see more details: ");
            int choice = scanner.nextInt() - 1;
            if(choice >= 0 && choice < matchMovies.size()){
                showMovieDetails(matchMovies.get(choice));
            }
        }
    }

    private void searchCast(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter part of a cast member's name: ");
        String search = scanner.next().toLowerCase();

        ArrayList<String> matchingCast = new ArrayList<>();
        ArrayList<Movie> matchedMovies = new ArrayList<>();

        for (Movie movie : list) {
            for (String actor : movie.getCast()) {
                if (actor.toLowerCase().contains(search)) {
                    if (!matchingCast.contains(actor)) {
                        matchingCast.add(actor);
                    }
                }
            }
        }

        if (matchingCast.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            matchingCast.sort(String::compareTo);
            for (int i = 0; i < matchingCast.size(); i++) {
                System.out.println((i + 1) + ". " + matchingCast.get(i));
            }

            System.out.println("Enter the number of the actor to see all movies they've been in: ");
            int choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < matchingCast.size()) {
                String selectedActor = matchingCast.get(choice);
                matchedMovies.clear();
                for (Movie movie : list) {
                    for (String actor : movie.getCast()) {
                        if (actor.equalsIgnoreCase(selectedActor)) {
                            matchedMovies.add(movie);
                        }
                    }
                }

                matchedMovies.sort(Comparator.comparing(Movie::getMovie));
                for (int i = 0; i < matchedMovies.size(); i++) {
                    System.out.println((i + 1) + ". " + matchedMovies.get(i).getMovie());
                }

                System.out.println("Enter the number of the movie to see more details: ");
                choice = scanner.nextInt() - 1;

                if (choice >= 0 && choice < matchedMovies.size()) {
                    showMovieDetails(matchedMovies.get(choice));
                }
            }
        }
    }

    private void showMovieDetails(Movie movie) {
        System.out.println("Title: " + movie.getMovie());
        System.out.println("Cast: " + String.join(", ", movie.getCast()));
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("User Rating: " + movie.getUserRating());
    }

    private void importData() {
        String filePath = "src/movies_data.csv";  // Ensure the correct file path is provided

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();  // Skip the header row
            int movieCount = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // Skip empty lines
                String[] fields = line.split(",");

                // Ensure there are exactly 6 fields per line (movie, cast, director, overview, runtime, userRating)
                if (fields.length == 6) {
                    String movieTitle = fields[0].trim();
                    String[] cast = fields[1].trim().split("\\|");
                    String director = fields[2].trim();
                    String overview = fields[3].trim();
                    int runtime = Integer.parseInt(fields[4].trim());
                    double userRating = Double.parseDouble(fields[5].trim());

                    Movie movie = new Movie(movieTitle, cast, director, overview, runtime, userRating);
                    list.add(movie);
                    movieCount++;
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }

            System.out.println("Movies loaded: " + movieCount);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
