package edu.ntnu.tobiasth.idatt2001.postalregistry.io;

import edu.ntnu.tobiasth.idatt2001.postalregistry.AppLogger;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalNumber;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File reader for Norwegian postal numbers.
 *
 * @author trthingnes
 */
public class PostalNumberReader implements FileReader {
  private final URL fileUrl;

  /**
   * Constructs a new reader.
   *
   * @param fileUrl File URL to read.
   */
  public PostalNumberReader(URL fileUrl) {
    this.fileUrl = fileUrl;
  }

  /** {@inheritDoc} */
  @Override
  public List<PostalCode> readFile() {
    List<PostalCode> list = new ArrayList<>();

    try (var reader = Files.newBufferedReader(Paths.get(fileUrl.toURI()))) {
      var skipped = new AtomicInteger();

      reader
          .lines()
          .forEach(
              line -> {
                try {
                  String[] data = line.split("\t");
                  var code = new PostalNumber(data[0], data[1], data[2], data[3], data[4]);
                  list.add(code);
                } catch (Exception e) {
                  skipped.getAndIncrement();
                }
              });

      if (skipped.get() > 0) {
        var warning = String.format("Skipped %s lines while importing from file.%n", skipped);
        AppLogger.get().warning(warning);
      }
    } catch (Exception e) {
      AppLogger.get()
          .warning(String.format("Failed to read file. Message: '%s'%n", e.getMessage()));
    }

    return list;
  }
}
