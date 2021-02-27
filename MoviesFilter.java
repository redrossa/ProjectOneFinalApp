import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A MoviesFilter instance provides a way to filter movies listed in a given CSV file
 * by genres and average ratings.
 */
public class MoviesFilter implements BackendInterface {
    /** A hash table that maps strings of genres and ratings to sorted sets of MovieInterfaces. */
    private HashTableMap<String, SortedSet<MovieInterface>> moviesMap;

    /** A list of all MovieInterfaces. */
    private List<MovieInterface> movies;

    /** A list of genres selected as filter. */
    private List<String> genresFilter;

    /** A list of all ratings selected as filter. */
    private List<String> ratingsFilter;


    /**
     * Initializes a MoviesFilter given the command line arguments.
     * @param args the command line arguments whose zeroth element is
     *        expected to be the path to a movies CSV file.
     * @throws IOException if the named file does not exist,
     *         is a directory rather than a regular file,
     *         or for some other reason cannot be opened for
     *         reading.
     */
    public MoviesFilter(String[] args) throws IOException {
        this(new FileReader(args[0]));
    }

    /**
     * Initializes a MoviesFilter given a Reader to a movies CSV file.
     * @param r a Reader to a movies CSV file.
     * @throws IOException if the named file does not exist,
     *         is a directory rather than a regular file,
     *         or for some other reason cannot be opened for
     *         reading.
     */
    public MoviesFilter(Reader r) throws IOException {
        movies = new MovieDataReader().readDataSet(r);
        genresFilter = new LinkedList<>();
        ratingsFilter = new LinkedList<>();
        List<String> genres = movies.stream()
                .flatMap(m -> m.getGenres().stream())
                .distinct()
                .collect(Collectors.toList());
        moviesMap = new HashTableMap<>(movies.size());
        for (String genre : genres) {
            SortedSet<MovieInterface> moviesWithGenre = movies.stream()
                    .filter(m -> m.getGenres().contains(genre))
                    .collect(Collectors.toCollection(TreeSet::new));
            moviesMap.put(genre, moviesWithGenre);
        }
        for (int rating = 0; rating <= 10; rating++) {
            int avgRate = rating;
            SortedSet<MovieInterface> moviesWithRating = movies.stream()
                    .filter(m -> avgRate <= m.getAvgVote() && m.getAvgVote() < avgRate + 1)
                    .collect(Collectors.toCollection(TreeSet::new));
            moviesMap.put(String.valueOf(rating), moviesWithRating);
        }
    }

    /**
     * Adds a genre into the filter.
     * @param genre a genre to add into the filter.
     */
    @Override
    public void addGenre(String genre) {
        if (genre != null) genresFilter.add(genre);
    }

    /**
     * Adds a rating into the filter.
     * @param rating a rating to add into the filter.
     */
    @Override
    public void addAvgRating(String rating) {
        if (rating != null) ratingsFilter.add(rating);
    }

    /**
     * Removes a genre in the filter.
     * @param genre the genre in the filter to remove.
     */
    @Override
    public void removeGenre(String genre) {
        genresFilter.remove(genre);
    }

    /**
     * Removes a rating in the filter.
     * @param rating the rating in the filter to remove.
     */
    @Override
    public void removeAvgRating(String rating) {
        ratingsFilter.remove(rating);
    }

    /**
     * Returns a list of genres in the filter.
     * @return a list of genres in the filter.
     */
    @Override
    public List<String> getGenres() {
        return genresFilter;
    }

    /**
     * Returns a list of ratings in the filter.
     * @return a list of ratings in the filter
     */
    @Override
    public List<String> getAvgRatings() {
        return ratingsFilter;
    }

    /**
     * Returns a list of all movies that match the filter.
     * @return a list of all movies that match the filter.
     */
    private List<MovieInterface> getFilteredMovies() {
        if (genresFilter.isEmpty())
            return new ArrayList<>();
        List<String> filter = Stream.concat(genresFilter.stream(), ratingsFilter.stream())
                .collect(Collectors.toList());
        SortedSet<MovieInterface> moviesSet = new TreeSet<>(movies);
        for (String key : filter)
            moviesSet.retainAll(moviesMap.get(key));
        return new ArrayList<>(moviesSet);
    }

    /**
     * Returns the number of movies that match the filter.
     * @return the number of movies that match the filter.
     */
    @Override
    public int getNumberOfMovies() {
        return getFilteredMovies().size();
    }

    /**
     * Returns a list of all genres of all movies in the internal dataset.
     * @return a list of all genres of all movies in the internal dataset.
     */
    @Override
    public List<String> getAllGenres() {
        return movies.stream()
                .flatMap(m -> m.getGenres().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of three movies that match the filter starting at (and
     * including) the movie at the given starting index ordered by average
     * movie rating in descending order. If there are less than three movies
     * left at the given index, the resulting list contains all those movies
     * (the list may be empty).
     * @param startingIndex the given starting index.
     * @return a list of at most three movies that match the filter starting
     * at the movie at the given starting index ordered in descending order
     * of the ratings.
     */
    @Override
    public List<MovieInterface> getThreeMovies(int startingIndex) {
        List<MovieInterface> movies = getFilteredMovies();
        try {
            return movies.subList(startingIndex, startingIndex + 3);
        } catch (IndexOutOfBoundsException e) {
            int size = movies.size();
            return (startingIndex < size) ? movies.subList(startingIndex, size) : new ArrayList<>();
        }
    }
}
