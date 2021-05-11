package edu.ntnu.tobiasth.idatt2001.postalregistry.io;

import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
import java.util.List;

/**
 * Interface for file readers.
 *
 * @author trthingnes
 */
public interface FileReader {
  /**
   * Reads postal codes from file.
   *
   * @return List of postal codes.
   */
  List<PostalCode> readFile();
}
