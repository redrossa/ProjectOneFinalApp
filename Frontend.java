import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.RunnableScheduledFuture;
//--== CS400 File Header Information ==--
//Name: Yuven Sundaramoorthy
//Email: ysundaramoor@wisc.edu
//Team: JF blue
//Role: Frontend Developer
//TA: Xinyi
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
public class Frontend {
  // Array lists to display the selected ratings and genres.
  private ArrayList<String> selectedGenres = new ArrayList<String>();
  private ArrayList<Integer> selectedRatings = new ArrayList<Integer>();

  /**
   * Initializes the Backend, and starts the frontend. Sets genres and ratings and
   * runs basemode.
   * 
   * @param args
   * @throws IOException
   */
  public void main(String[] args) throws IOException {
    // Initializes backend
    Backend backend = new Backend(args);
    // Prints to the console for an introduction
    System.out.println("Welcome to the Movie Database");
    System.out.println("=============================");
    // Adds all genres in order to display top 3 movies overall.
    backend.addGenre("all");
    // Adds all the ratings to show top 3 movies overall.
    for (int i = 0; i < 11; i++) {
      backend.addAvgRating(String.valueOf(i));
    }
    // Initializes the selectedratings arraylist.
    for (int i = 0; i < 11; i++) {
      selectedRatings.add(i);
    }
    // Console instructions
    System.out.println("(Showing top movies overall, press g to select genre)");
    // runs the basemode
    baseMode(backend);
  }

  /**
   * Runs the genre mode for the program. Allows activation and deactivation of
   * genres, that add and remove them from the backend.
   * 
   * @param backend
   */
  private void genreMode(Backend backend) {
    // Removes the all tag from genre to allow use of genre selector.
    backend.removeGenre("all");
    // Initializes selectedgenre arraylist to have blank sections.
    for (int i = 0; i < backend.getAllGenres().size(); i++) {
      selectedGenres.add("");
    }
    // Initializes the scanner
    Scanner keyboard = new Scanner(System.in);
    // loop to keep user in genre mode until x is pressed.
    while (true) {
      // Console introduction
      System.out.println();
      System.out.println("List of Genres (Caps is selected):");
      // Checks if genres are selected or not then prints them to console to display
      // list
      for (int i = 0; i < backend.getAllGenres().size(); i++) {
        String current = backend.getAllGenres().get(i);
        if (selectedGenres.contains(String.valueOf(i)))
          System.out.println(i + 1 + ". " + current.toUpperCase());
        else
          System.out.println(i + 1 + ". " + current);
      }
      // Console instructions
      System.out.println("Type number to select/deselect");
      // Checks what is typed by user
      String key = keyboard.nextLine();
      // Adds or removes from backend
      try {
        if (selectedGenres.get(Integer.parseInt(key) - 1).equals("")) {
          backend.addGenre(backend.getAllGenres().get(Integer.parseInt(key) - 1));
          selectedGenres.set(Integer.parseInt(key) - 1,
              String.valueOf((Integer.parseInt(key)) - 1));

        } else {
          backend.removeGenre(backend.getAllGenres().get(Integer.parseInt(key) - 1));
          selectedGenres.set(Integer.parseInt(key) - 1, "");
        }
      } catch (Exception e) {
        // checks if x is pressed to exit
        if (key.equalsIgnoreCase("x")) {
          baseMode(backend);
        }
      }
    }
  }

  /**
   * Runs the rating mode for the program. Allows activation and deactivation of
   * ratings, that add and remove them from the backend.
   * 
   * @param backend
   */
  private void ratingMode(Backend backend) {
    // initialize scanner
    Scanner keyboard = new Scanner(System.in);
    // loop to stay in rating mode until x is pressed
    while (true) {
      // Console introduction
      System.out.println();
      System.out.println("List of Ratings (parentheses means selected):");
      // Checks what ratings are selected then displays them as a list
      for (int i = 0; i < 11; i++) {
        if (selectedRatings.contains(i))
          System.out.println("(" + (i) + ")");
        else
          System.out.println(i);
      }
      System.out.println("Type number to select/deselect");
      // Checks what key is pressed
      String key = keyboard.nextLine();
      // Adds or removes from backend
      try {
        if (selectedRatings.get(Integer.parseInt(key)).equals(-1)) {
          backend.addAvgRating(String.valueOf(Integer.parseInt(key)));
          selectedRatings.set(Integer.parseInt(key), (Integer.parseInt(key)));

        } else {
          backend.removeAvgRating(String.valueOf(Integer.parseInt(key)));
          selectedRatings.set(Integer.parseInt(key), -1);
        }
      } catch (Exception e) {
        if (key.equalsIgnoreCase("x")) {
          baseMode(backend);
        }
      }
    }
  }

  /**
   * The base mode for the program. Displays the top three movies that also match
   * the genre and ratings selected.
   * 
   * @param backend
   */
  private void baseMode(Backend backend) {
    // initialize scanner
    Scanner keyboard = new Scanner(System.in);
    String key = "1";
    // Console introduction
    System.out.println();
    System.out.println("Top Movies: ");
    System.out.println("(Search by ranking by entering number)");
    System.out.println();
    // Checks if no movies are selected
    if (backend.getNumberOfMovies() == 0)
      System.out.println("No genre or rating selected. Enter number or press g or r to proceed");
    // prints the top 3 movies
    try {
      System.out.println(Integer.parseInt(key) + ". "
          + backend.getThreeMovies(Integer.parseInt(key) - 1).get(0).getTitle());
    } catch (Exception e) {
      try {
        if (Integer.parseInt(key) > backend.getNumberOfMovies())
          System.out.println("Only " + backend.getNumberOfMovies() + " movies selected.");
        else {
          key = "1";
        }
      } catch (Exception g) {
        System.out.println("Key not understood, try again.");
      }
    }
    try {
      System.out.println((Integer.parseInt(key) + 1) + ". "
          + backend.getThreeMovies(Integer.parseInt(key) - 1).get(1).getTitle());
      System.out.println((Integer.parseInt(key) + 2) + ". "
          + backend.getThreeMovies(Integer.parseInt(key) - 1).get(2).getTitle());
    } catch (Exception e) {
    }
    key = keyboard.nextLine();
    // loop to stay in mode
    while (true) {
      // if x is pressed quit.
      if (key.equalsIgnoreCase("x")) {
        System.out.println("Thank you, Come Again");
        System.exit(0);
      }
      // if no movies are selected
      if (backend.getNumberOfMovies() == 0)
        System.out.println("No genre or rating selected. Enter number or press g or r to proceed");
      // if g is pressed
      if (key.equalsIgnoreCase("g")) {
        genreMode(backend);
        return;
      }
      // if r is pressed
      if (key.equalsIgnoreCase("r")) {
        ratingMode(backend);
        return;
      } else
        System.out.println();
      System.out.println("Top Movies: ");
      //Prints new top 3 movies
      try {
        System.out.println(Integer.parseInt(key) + ". "
            + backend.getThreeMovies(Integer.parseInt(key) - 1).get(0).getTitle());
      } catch (Exception e) {
        try {
          if (Integer.parseInt(key) > backend.getNumberOfMovies())
            System.out.println("Only " + backend.getNumberOfMovies() + " movies selected.");
          else {
            key = "1";
            System.out.println(Integer.parseInt(key) + ". "
                + backend.getThreeMovies(Integer.parseInt(key) - 1).get(0).getTitle());
          }
        } catch (Exception g) {
          System.out.println("Key not understood, try again.");
        }
      }
      try {
        System.out.println((Integer.parseInt(key) + 1) + ". "
            + backend.getThreeMovies(Integer.parseInt(key) - 1).get(1).getTitle());
      } catch (Exception e) {
      }
      try {
        System.out.println((Integer.parseInt(key) + 2) + ". "
            + backend.getThreeMovies(Integer.parseInt(key) - 1).get(2).getTitle());
      } catch (Exception e) {
      }
      key = keyboard.nextLine();
    }
  }
}