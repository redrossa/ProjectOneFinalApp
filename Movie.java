import java.util.Arrays;
import java.util.List;
import java.util.Objects;
//--== CS400 File Header Information ==--
//Name: Lilly Boyd
//Email: laboyd2@wisc.edu
//Team: JF blue
//Role: Data Wrangler
//TA: Xinyi 
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
public class Movie implements MovieInterface {
    private String title;
    private int year;
    private List<String> genres;
    private String director;
    private String desc;
    private float avgVote;

    /**
     * Constructor to set values of each movie
     * @param title - movie title
     * @param year - year released
     * @param genres - genres of movie
     * @param director - director of movie
     * @param desc - movie description
     * @param avgVote - average vote of movie
     */
    public Movie(String title, int year, String[] genres, String director, String desc, float avgVote) {
        this.title = title;
        this.year = year;
        this.genres = Arrays.asList(genres);
        this.director = director;
        this.desc = desc;
        this.avgVote = avgVote;
    }

    /**
     * returns the title of the movie
     */
    @Override
    public String getTitle() {
        return title;
    }
    /**
     * returns the year of the movie
     */
    @Override
    public Integer getYear() {
        return year;
    }

    /**
     * returns the genres in for the movie
     */
    @Override
    public List<String> getGenres() {
        return genres;
    }

    /**
     * returns name of director
     */
    @Override
    public String getDirector() {
        return director;
    }

    /**
     * returns description for the movie
     */
    @Override
    public String getDescription() {
        return desc;
    }

    /**
     * returns the average vote for the movie
     */
    @Override
    public Float getAvgVote() {
        return avgVote;
    }

    /**
     * compares ratings between movies
     */
    @Override
    public int compareTo(MovieInterface otherMovie) {
        if (this.getTitle().equals(otherMovie.getTitle())) {
            return 0;
            // sort by rating
        } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
            return +1;
        } else {
            return -1;
        }
    }
    
    /**
     * overrides equals() to compare fields of movies to each other
     * @param o - movie to be compared
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Float.compare(movie.avgVote, avgVote) == 0 &&
                Objects.equals(title, movie.title) &&
                Objects.equals(genres, movie.genres) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(desc, movie.desc);
    }

    /**
     * creates hash code for movie object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, year, genres, director, desc, avgVote);
    }
}
