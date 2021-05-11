package edu.ntnu.tobiasth.idatt2001.postalregistry;

import edu.ntnu.tobiasth.idatt2001.postalregistry.io.FileReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.io.PostalNumberReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
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
    TableColumn<PostalCode, String> typeCol =
        new TableColumnBuilder<PostalCode, String>()
            .setText("Type")
            .setCellPropertyValue("TypeDescription")
            .setFitContentProperty()
            .build();
    TableColumn<PostalCode, String> codeCol = createChildColumn("Code", "Code");
    TableColumn<PostalCode, String> locationCol = createChildColumn("Location", "LocationName");
    TableColumn<PostalCode, String> provinceNumberCol = createChildColumn("Number", "ProvinceCode");
    TableColumn<PostalCode, String> provinceNameCol = createChildColumn("Name", "ProvinceName");

    TableColumn<PostalCode, String> postalCodeCol =
        createParentColumn("Postal Code", List.of(codeCol, locationCol, typeCol));
    TableColumn<PostalCode, String> provinceCol =
        createParentColumn("Province", List.of(provinceNumberCol, provinceNameCol));

    listTable.getColumns().addAll(List.of(postalCodeCol, provinceCol));

    // Add table content.
    FileReader reader =
        new PostalNumberReader(App.class.getResource("/data/nor_postal_numbers.dat"));
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
   * Creates a basic table column using the builder.
   *
   * @param text Header text.
   * @param property Property to link column to.
   * @return Table column.
   */
  private TableColumn<PostalCode, String> createChildColumn(String text, String property) {
    return new TableColumnBuilder<PostalCode, String>()
        .setText(text)
        .setCellPropertyValue(property)
        .build();
  }

  /**
   * Creates a parent table column using the builder.
   *
   * @param text Header text.
   * @param children Child columns to fill parent column with.
   * @return Table column.
   */
  private TableColumn<PostalCode, String> createParentColumn(
      String text, List<TableColumn<PostalCode, String>> children) {
    return new TableColumnBuilder<PostalCode, String>()
        .setText(text)
        .addNestedColumns(children)
        .build();
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
