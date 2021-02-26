import java.util.Arrays;
import java.util.List;

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
}
