package edu.ntnu.tobiasth.idatt2001.postalregistry;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class for application {@link Logger}.
 *
 * <p>Waits for first method call to construct logger (lazy initialization).
 */
public class AppLogger {
  private static Logger logger;

  /** Unused constructor. */
  private AppLogger() {}

  /**
   * Gets the application logger.
   *
   * @return Logger.
   */
  public static Logger get() {
    if (Objects.isNull(logger)) {
      logger = Logger.getLogger("idatt2001.postalregistry");
      logger.setLevel(Level.ALL);
    }

    return logger;
  }
}
