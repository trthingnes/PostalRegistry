package edu.ntnu.tobiasth.idatt2001.postalregistry;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NorFileReader implements FileReader {
  private final URI fileUri;

  public NorFileReader(URI fileUri) {
    this.fileUri = fileUri;
  }

  @Override
  public List<PostalCode> readFile() {
    List<PostalCode> list = new ArrayList<>();

    try (var reader = Files.newBufferedReader(Paths.get(fileUri))) {
      var skipped = new AtomicInteger();

      reader
          .lines()
          .forEach(
              line -> {
                try {
                  String[] data = line.split("\t");
                  var code = new NorPostalCode(data[0], data[1], data[2], data[3], data[4]);
                  list.add(code);
                } catch (IllegalArgumentException e) {
                  skipped.getAndIncrement();
                }
              });

      if(skipped.get() > 0) {
        var warning = String.format("Skipped %s postal codes while importing from file.%n", skipped);
        AppLogger.get().warning(warning);
      }
    } catch (Exception e) {
      AppLogger.get()
          .warning(String.format("Failed to read file. Message: '%s'%n", e.getMessage()));
    }

    return list;
  }
}
