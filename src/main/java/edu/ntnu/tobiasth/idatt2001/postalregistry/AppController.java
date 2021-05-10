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

public class AppController {
  @FXML private TableView<PostalCode> table;
  @FXML private TextField searchbar;

  private FilteredList<PostalCode> tableList;

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
  }

  @FXML
  void filter() {
    String query = searchbar.getText().toLowerCase();

    Predicate<PostalCode> predicate =
        code ->
            code.getCode().contains(query) || code.getLocationName().toLowerCase().contains(query);

    tableList.setPredicate(predicate);
  }
}
