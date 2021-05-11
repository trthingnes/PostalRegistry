package edu.ntnu.tobiasth.idatt2001.postalregistry;

import java.io.InputStream;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main JavaFX application class.
 *
 * @author trthingnes
 */
public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    var loader = new FXMLLoader(App.class.getResource("/app.fxml"));
    var scene = new Scene(loader.load());

    try {
      InputStream imageStream = App.class.getResourceAsStream("/logo.png");
      primaryStage.getIcons().add(new Image(Objects.requireNonNull(imageStream)));
    } catch (NullPointerException ignored) {
      /* No icon is added if load fails */
    }

    primaryStage.setScene(scene);
    primaryStage.setTitle("Postal Code Register");
    primaryStage.centerOnScreen();
    primaryStage.show();
  }
}
