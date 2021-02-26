import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MoviesFilter implements BackendInterface {
    private HashTableMap<String, SortedSet<MovieInterface>> moviesMap;
    private List<MovieInterface> movies;
    private List<String> genresFilter;
    private List<String> ratingsFilter;


    public MoviesFilter(String[] args) {
        // TODO implement constructor from command line arguments
    }

    public MoviesFilter(Reader r) {
        // TODO implement constructor from reader
    }

    /**
     * Adds a genre into the filter.
     * @param genre a genre to add into the filter
     */
    @Override
    public void addGenre(String genre) {
        if (genre != null) genresFilter.add(genre);
    }

    /**
     * Adds a rating into the filter.
     * @param rating a rating to add into the filter
     */
    @Override
    public void addAvgRating(String rating) {
        if (rating != null) ratingsFilter.add(rating);
    }

    /**
     * Removes a genre in the filter.
     * @param genre the genre in the filter to remove
     */
    @Override
    public void removeGenre(String genre) {
        genresFilter.remove(genre);
    }

    /**
     * Removes a rating in the filter.
     * @param rating the rating in the filter to remove
     */
    @Override
    public void removeAvgRating(String rating) {
        ratingsFilter.remove(rating);
    }

    /**
     * Returns a list of genres in the filter.
     * @return a list of genres in the filter
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
     * @return a list of all movies that match the filter
     */
    private List<MovieInterface> getFilteredMovies() {
        List<String> filter = Stream.concat(genresFilter.stream(), ratingsFilter.stream())
                .collect(Collectors.toList());
        SortedSet<MovieInterface> moviesSet = new TreeSet<>(movies);
        for (String key : filter)
            moviesSet.retainAll(moviesMap.get(key));
        return new ArrayList<>(moviesSet);
    }

    /**
     * Returns the number of movies that match the filter.
     * @return the number of movies that match the filter
     */
    @Override
    public int getNumberOfMovies() {
        return getFilteredMovies().size();
    }

    /**
     * Returns a list of all genres of all movies in the internal dataset.
     * @return a list of all genres of all movies in the internal dataset
     */
    @Override
    public List<String> getAllGenres() {
        return movies.stream()
                .flatMap(m -> m.getGenres().stream())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of three movies that match the filter starting at (and
     * including) the movie at the given starting index ordered by average
     * movie rating in descending order. If there are less than three movies
     * left at the given index, the resulting list contains all those movies
     * (the list may be empty).
     * @param startingIndex the given starting index
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
            int size = getNumberOfMovies();
            return (startingIndex < size) ? movies.subList(startingIndex, size) : new ArrayList<>();
        }
    }
}
