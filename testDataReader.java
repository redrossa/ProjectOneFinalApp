import java.io.FileReader;
import java.util.List;

//--== CS400 File Header Information ==--
//Name: Lilly Boyd
//Email: laboyd2@wisc.edu
//Team: JF blue
//Role: Data Wrangler
//TA: Xinyi 
//Lecturer: Gary
//Notes to Grader: <optional extra notes>
public class testDataReader {
  static MovieDataReader readerToTest;
  
  public static boolean test () {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new FileReader("movies.csv"));
      for (int i = 0; i < movieList.size(); i++) {
        System.out.println(movieList.get(i).toString());
      }
      
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
