package edu.ntnu.tobiasth.idatt2001.postalregistry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class.
 *
 * @author trthingnes
 */
public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    var loader = new FXMLLoader(getClass().getResource("/app.fxml"));
    var scene = new Scene(loader.load());

    primaryStage.setScene(scene);
    primaryStage.setTitle("Postal Code Register");
    primaryStage.setMinWidth(1200);
    primaryStage.setMinHeight(800);
    primaryStage.centerOnScreen();
    primaryStage.show();
  }
}
