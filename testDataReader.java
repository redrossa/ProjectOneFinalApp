import java.io.FileReader;
import java.util.List;

public class testDataReader {
  static MovieDataReader readerToTest;
  
  public static boolean test () {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new FileReader("moviesOneGenre.csv"));
      
    }
    catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    
    return true;
  }
  
  public static void main(String[] args) {
   readerToTest = new MovieDataReader();
   System.out.println(test());
   

  }

}
