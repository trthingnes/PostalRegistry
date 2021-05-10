package edu.ntnu.tobiasth.idatt2001.postalregistry.factory;

import edu.ntnu.tobiasth.idatt2001.postalregistry.App;
import javafx.fxml.FXMLLoader;

/**
 * Generates {@link FXMLLoader} objects.
 *
 * @author trthingnes
 */
public class FxmlLoaderFactory {
  private FxmlLoaderFactory() {}

  /**
   * Gets an FXML loader for the given FXML file.
   *
   * @param name Name of the FXML file in the FXML folder.
   * @return FXML loader
   */
  public static FXMLLoader getFxmlLoader(String name) {
    var path = String.format("/fxml/%s.fxml", name);
    return new FXMLLoader(App.class.getResource(path));
  }
}
