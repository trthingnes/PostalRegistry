package edu.ntnu.tobiasth.idatt2001.postalregistry;

import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
import edu.ntnu.tobiasth.idatt2001.postalregistry.util.FileReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.util.PostalNumberReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.util.TableColumnBuilder;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Main controller for the JavaFX application.
 *
 * @author trthingnes
 */
public class AppController {
  @FXML private TableView<PostalCode> listTable;
  @FXML private TextField searchField;
  @FXML private Button clearButton;

  /** Initializes the GUI. */
  @FXML
  void initialize() {
    // Postal Code Column
    TableColumn<PostalCode, String> codeCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Code")
            .setCellPropertyValue("Code")
            .build();
    TableColumn<PostalCode, String> locationCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Location")
            .setCellPropertyValue("LocationName")
            .build();
    TableColumn<PostalCode, String> typeCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Type")
            .setFitContentProperty()
            .setCellPropertyValue("TypeDescription")
            .build();
    TableColumn<PostalCode, String> postalCodeCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Postal Code")
            .addNestedColumns(List.of(codeCol, locationCol, typeCol))
            .build();

    // Province Column
    TableColumn<PostalCode, String> provinceNumberCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Number")
            .setCellPropertyValue("ProvinceCode")
            .build();
    TableColumn<PostalCode, String> provinceNameCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Location")
            .setCellPropertyValue("ProvinceName")
            .build();
    TableColumn<PostalCode, String> provinceCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Province")
            .addNestedColumns(List.of(provinceNameCol, provinceNumberCol))
            .build();

    // Add columns to table.
    listTable.getColumns().addAll(List.of(postalCodeCol, provinceCol));

    // Add table content.
    FileReader reader = new PostalNumberReader(App.class.getResource("/nor_postal_numbers.dat"));
    FilteredList<PostalCode> list =
        new FilteredList<>(FXCollections.observableList(reader.readFile()));
    listTable.setItems(list);

    // Set search listener.
    searchField
        .textProperty()
        .addListener(
            ((observable, oldValue, newValue) -> list.setPredicate(createPredicate(newValue))));

    // Set clear listener.
    clearButton.setOnMouseClicked(event -> searchField.setText(""));
  }

  /**
   * Creates a predicate for use with the table filtered list.
   *
   * @param query Search query to match with table entry.
   * @return Predicate.
   */
  private Predicate<PostalCode> createPredicate(String query) {
    return code ->
        code.getCode().contains(query)
            || code.getLocationName().toLowerCase().contains(query.toLowerCase());
  }
}
