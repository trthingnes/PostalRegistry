package edu.ntnu.tobiasth.idatt2001.postalregistry;

import edu.ntnu.tobiasth.idatt2001.postalregistry.factory.TableColumnFactory;
import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalCode;
import edu.ntnu.tobiasth.idatt2001.postalregistry.util.FileReader;
import edu.ntnu.tobiasth.idatt2001.postalregistry.util.NorFileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Main controller for the JavaFX application.
 *
 * @author trthingnes
 */
public class AppController {
  @FXML private TableView<PostalCode> table;
  @FXML private TextField searchbar;

  private FilteredList<PostalCode> tableList;

  /** Initializes the GUI. */
  @FXML
  void initialize() {
    // Add table columns.
    List<TableColumn<PostalCode, String>> columns = new ArrayList<>();
    var columnNames =
        new String[][] {
          {"Postal Code", "Code"},
          {"Postal Location", "LocationName"},
          {"Province Code", "ProvinceCode"},
          {"Province Name", "ProvinceName"},
          {"Type", "TypeDescription"}
        };
    for (String[] columnName : columnNames) {
      columns.add(TableColumnFactory.getTableColumn(columnName[0], columnName[1]));
    }
    table.getColumns().addAll(columns);

    // Add table content.
    FileReader reader = new NorFileReader(App.class.getResource("/nor_postal_numbers.dat"));
    tableList = new FilteredList<>(FXCollections.observableList(reader.readFile()));
    table.setItems(tableList);

    // Set search listener.
    searchbar
        .textProperty()
        .addListener(
            ((observable, oldValue, newValue) ->
                tableList.setPredicate(createPredicate(newValue))));
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
