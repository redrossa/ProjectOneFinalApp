import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Frontend {
  private ArrayList<String> selectedGenres = new ArrayList<String>();
  private ArrayList<Integer> selectedRatings = new ArrayList<Integer>();

  public void main(String[] args) throws IOException {
    Backend backend = new Backend(args);
    System.out.println("Welcome to the Movie Database");
    System.out.println("=============================");
    backend.addGenre("all");
    for (int i = 0; i < 11; i++) {
      backend.addAvgRating(String.valueOf(i));
    }
    for (int i = 0; i < 11; i++) {
      selectedRatings.add(i);
    }
    System.out.println("(Showing top movies overall, press g to select genre)");
    baseMode(backend);
    System.out.println("");
  }

  private void genreMode(Backend backend) {
    backend.removeGenre("all");
    for (int i = 0; i < backend.getAllGenres().size(); i++) {
      selectedGenres.add("");
    }
    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.println();
      System.out.println("List of Genres (Caps is selected):");
      for (int i = 0; i < backend.getAllGenres().size(); i++) {
        String current = backend.getAllGenres().get(i);
        if (selectedGenres.contains(String.valueOf(i)))
          System.out.println(i + 1 + ". " + current.toUpperCase());
        else
          System.out.println(i + 1 + ". " + current);
      }
      System.out.println("Type number to select/deselect");
      String key = keyboard.nextLine();
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
        if (key.equalsIgnoreCase("x")) {
          baseMode(backend);
        }
      }
    }
  }

  private void ratingMode(Backend backend) {
    
    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.println();
      System.out.println("List of Ratings (parentheses means selected):");
      for (int i = 0; i < 11; i++) {
        if (selectedRatings.contains(i))
          System.out.println("(" + (i) + ")");
        else
          System.out.println(i);
      }
      System.out.println("Type number to select/deselect");
      String key = keyboard.nextLine();
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

  private void baseMode(Backend backend) {
    Scanner keyboard = new Scanner(System.in);
    String key = "1";
    System.out.println();
    System.out.println("Top Movies: ");
    System.out.println("(Search by ranking by entering number)");
    System.out.println();
    if (backend.getNumberOfMovies() == 0)
      System.out.println("No genre or rating selected. Enter number or press g or r to proceed");
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
    while (!key.equalsIgnoreCase("g") || !key.equalsIgnoreCase("r") || !key.equalsIgnoreCase("x")) {
      if (key.equalsIgnoreCase("x")) {
        System.out.println("Thank you, Come Again");
        System.exit(0);
      }
      if (backend.getNumberOfMovies() == 0)
        System.out.println("No genre or rating selected. Enter number or press g or r to proceed");
      if (key.equalsIgnoreCase("g")) {
        genreMode(backend);
        return;
      }
      if (key.equalsIgnoreCase("r")) {
        ratingMode(backend);
        return;
      } else
        System.out.println();
        System.out.println("Top Movies: ");
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
    if (key.equalsIgnoreCase("x")) {
      System.exit(0);
    } else if (key.equalsIgnoreCase("g")) {
      genreMode(backend);
      return;

    } else if (key.equalsIgnoreCase("r")) {
      ratingMode(backend);
      return;
    }
  }
}
