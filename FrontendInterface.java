import java.io.IOException;
import java.util.Scanner;

public class FrontendInterface {
  
  public void main(String[] args) throws IOException {
    Backend backend = new Backend(args);
    try {
    Runtime.getRuntime().exec("clear");
    }catch (Exception e) {}
    System.out.println("Welcome to the Movie Database");
    backend.addGenre("all");
    for(int i=0; i<11;i++) {
      backend.addAvgRating(String.valueOf(i));
    }
    System.out.println(backend.getNumberOfMovies());
    baseMode(backend);
  }
  private static void genreMode(Backend backend) {
    for(int i =0; i<backend.getAllGenres().size();i++) {
      backend.removeGenre(backend.getAllGenres().get(i));
    }
 
  }

  private static void ratingMode(Backend backend) {
    for(int i =0; i<11;i++) {
      backend.removeAvgRating(String.valueOf(i));
    }
  }

  private static void baseMode(Backend backend) {
    Scanner keyboard = new Scanner(System.in);
    String key = "0";
    System.out.println("Top 3 Movies: ");
    try {
      System.out.println(backend.getThreeMovies(Integer.parseInt(key)).get(0).getTitle());
    } catch (Exception e) {
      System.out.println("No genre or rating selected. Press g or r to proceed");
    }
    System.out.println(backend.getThreeMovies(Integer.parseInt(key)).get(1).getTitle());
    System.out.println(backend.getThreeMovies(Integer.parseInt(key)).get(2).getTitle());
    key = keyboard.nextLine();
    try {
      Runtime.getRuntime().exec("clear");
    } catch (IOException e) {

    }
    while (!key.equalsIgnoreCase("g") || !key.equalsIgnoreCase("r")) {
      if (key.equalsIgnoreCase("g")) {
        genreMode(backend);
        return;
      } else if (key.equalsIgnoreCase("r")) {
        ratingMode(backend);
        return;
      }
      System.out.println("Top 3 Movies: ");
      try {
        System.out.println(backend.getThreeMovies(Integer.parseInt(key)-1).get(0).getTitle());
      } catch (Exception e) {
        System.out.println("No genre or rating selected. Press g or r to proceed");
      }
      try {
        System.out.println(backend.getThreeMovies(Integer.parseInt(key)-1).get(1).getTitle());
      } catch (Exception e) {}
      try {
        System.out.println(backend.getThreeMovies(Integer.parseInt(key)-1).get(2).getTitle());
      } catch (Exception e) {}
      key = keyboard.nextLine();
      try {
        Runtime.getRuntime().exec("clear");
      } catch (IOException e) {}
    }
  }
}
