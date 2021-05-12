import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.tobiasth.idatt2001.postalregistry.App;
import edu.ntnu.tobiasth.idatt2001.postalregistry.io.PostalNumberReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalNumber;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Norwegian Postal Number Reader Tests")
class PostalNumberReaderTest {
  private final URL validFileUrl = App.class.getResource("/valid_postal_numbers.dat");
  private final URL invalidFileUrl = App.class.getResource("/invalid_postal_numbers.dat");

  @Test
  @DisplayName("Valid file reads successfully")
  void testValidFileReadsSuccessfully() {
    PostalNumberReader reader = new PostalNumberReader(validFileUrl);
    List<PostalCode> postalNumbers = reader.readFile();

    assertEquals(5, postalNumbers.size());
  }

  @Test
  @DisplayName("Data maps to object correctly")
  void testDataMapsToObjectCorrectly() {
    PostalNumberReader reader = new PostalNumberReader(validFileUrl);
    List<PostalCode> postalNumbers = reader.readFile();
    PostalNumber expected = new PostalNumber("7003", "TRONDHEIM", "5001", "TRONDHEIM", "P");

    assertEquals(expected, postalNumbers.get(0));
  }

  @Test
  @DisplayName("Invalid file returns no results")
  void testInvalidFileReturnsNoResult() {
    PostalNumberReader reader = new PostalNumberReader(invalidFileUrl);
    List<PostalCode> postalNumbers = reader.readFile();

    assertEquals(0, postalNumbers.size());
  }
}
