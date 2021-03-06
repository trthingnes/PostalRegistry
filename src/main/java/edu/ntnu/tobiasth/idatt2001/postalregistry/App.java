package edu.ntnu.tobiasth.idatt2001.postalregistry;

import java.io.InputStream;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main JavaFX application class.
 *
 * @author trthingnes
 */
public class App extends Application {
  /** {@inheritDoc} */
  @Override
  public void start(Stage primaryStage) throws Exception {
    var loader = new FXMLLoader(App.class.getResource("/app.fxml"));
    var scene = new Scene(loader.load());

    try {
      InputStream imageStream = App.class.getResourceAsStream("/img/logo.png");
      primaryStage.getIcons().add(new Image(Objects.requireNonNull(imageStream)));
    } catch (NullPointerException ignored) {
      /* No icon is added if load fails */
    }

    primaryStage.setOnCloseRequest(
        event -> {
          Dialog<ButtonType> dialog = new Dialog<>();
          dialog.setHeaderText("Confirm exit");
          dialog.setContentText("Are you sure you want to close the application?");
          dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

          if (dialog.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
            event.consume();
          }
        });

    primaryStage.setScene(scene);
    primaryStage.setTitle("Postal Code Register");
    primaryStage.setMinHeight(200);
    primaryStage.setHeight(800);
    primaryStage.setMinWidth(500);
    primaryStage.centerOnScreen();
    primaryStage.show();
  }
}
