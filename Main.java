import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    Backend backend = new Backend(new FileReader("movies.csv"));
    backend.addGenre("Drama");
    for (int i = 0; i <= 10; i++)
      backend.addAvgRating(String.valueOf(i));
    System.out.println("end");
  }
}
