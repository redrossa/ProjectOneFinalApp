import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// --== CS400 File Header Information ==--
// Name: Lilly Boyd
// Email: laboyd2@wisc.edu
// Team: JF blue
// Role: Data Wrangler
// TA: Xinyi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
public class MovieDataReader implements MovieDataReaderInterface {

  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException {
    // TODO: throw exceptions somehow
    Scanner csvScanner = new Scanner(inputFileReader);
    List<Movie> movies = new ArrayList<Movie>();
    while (csvScanner.hasNext()) {
      String line = csvScanner.nextLine();

      Scanner lineScanner = new Scanner(line);
      while (lineScanner.hasNext()) {
        csvScanner = new Scanner(lineScanner.next());
        csvScanner.useDelimiter(",");

      }
      lineScanner.close();
    }
    csvScanner.close();

    return null;
  }

}
