import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// --== CS400 File Header Information ==--
// Name: Lilly Boyd
// Email: laboyd2@wisc.edu
// Team: JF blue
// Role: Data Wrangler
// TA: Xinyi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
public class MovieDataReader implements MovieDataReaderInterface {

  /**
   * reads movie info from csv and creates Movie objects
   * @param inputFileReader - file to be read from 
   * @returns movies list
   * @throws FileNotFoundException when file parameter doesn't match 
   */
  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException {
    List<MovieInterface> movies = new ArrayList<MovieInterface>();
    String line = "";
    String splitBy = ",";
    BufferedReader br = new BufferedReader(inputFileReader);
    // returns boolean value
    while ((line = br.readLine()) != null) {
      String movie[] = line.split(splitBy);
      for (int i = 0; i < movie.length; i++) {
       
      }
    }
    
    return movies;
  }

}
