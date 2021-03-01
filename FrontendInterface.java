import java.io.IOException;
import java.util.Scanner;

public class FrontendInterface {
  public static void main(String[] args) throws IOException {
    Backend backend = new Backend(args);
    System.out.println("Welcome to the Movie Database");
    backend.addGenre("Drama");
    backend.addAvgRating("6");
    baseMode(backend);
  }

  private static void selectMode() {

  }

  private static void genreMode() {

  }

  private static void ratingMode() {

  }

  private static void baseMode(Backend backend) {
    Scanner keyboard = new Scanner(System.in);
    String key = "";
    while (!key.equalsIgnoreCase("x")) {
      System.out.println("Top 3 Movies: ");
      try {
        System.out.println(backend.getThreeMovies(0).get(0).getTitle());
      } catch (Exception e) {
        System.out.println("No genre or rating selected. Press g or r to proceed");
        String val = keyboard.nextLine();
        // if(val.equalsIgnoreCase("g"))
      }
      System.out.println(backend.getThreeMovies(0).get(1).getTitle());
      System.out.println(backend.getThreeMovies(0).get(2).getTitle());
      key = keyboard.nextLine();
    }
    try {
      Runtime.getRuntime().exec("cls");
    } catch (IOException e) {
      
    }
  }
}
