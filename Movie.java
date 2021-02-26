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


    public Movie(String title, int year, String[] genres, String director, String desc, float avgVote) {
        this.title = title;
        this.year = year;
        this.genres = Arrays.asList(genres);
        this.director = director;
        this.desc = desc;
        this.avgVote = avgVote;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public Float getAvgVote() {
        return avgVote;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(title, year, genres, director, desc, avgVote);
    }
}
