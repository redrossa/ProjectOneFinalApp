import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
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
   * 
   * @param inputFileReader - file to be read from
   * @returns movies list
   * @throws FileNotFoundException when file parameter doesn't match
   */
  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException {
    List<MovieInterface> movies = new ArrayList<MovieInterface>();
    CSVReader reader = new CSVReader(inputFileReader);
    int count = 0;
    for (String[] line : reader) {
      if (count == 0) {
        count++;
        continue;
      }
      count++;
      String splitBy = ", ";
      String[] genres = line[3].split(splitBy);
      String yearVal = line[2].toString();
      movies.add(new Movie(line[0], Integer.parseInt(yearVal), genres, line[7], line[11],
          Float.parseFloat(line[12])));
    }
    return movies;
  }

}
