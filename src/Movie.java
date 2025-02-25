public class Movie {
    private String movie;
    private  String[] cast;
    private String director;
    private String overview;
    private int runtime;
    private  double  userRating;

    public Movie(String movie, String[] cast,String director, String overview, int runtime, double userRating ){
        this.movie = movie;
        this.cast = cast;
        this.director =director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;

    }
    public String getMovie() {
        return  movie;
    }
    public String[] getCast(){
        return cast;
    }
    public String getDirector(){
        return director;
    }
    public String getOverview(){
        return overview;
    }
    public int getRuntime(){
        return  runtime;
    }
    public double getUserRating(){
        return  userRating;
    }
}
