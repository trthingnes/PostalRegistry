package edu.ntnu.tobiasth.idatt2001.postalregistry.factory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Generates {@link TableColumn} objects.
 *
 * @author trthingnes
 */
public class TableColumnFactory {
  private TableColumnFactory() {}

  /**
   * Gets a table column with the given header and property value.
   *
   * @param header Column header.
   * @param propertyValue Property value of column.
   * @return Table column.
   */
  public static <S, T> TableColumn<S, T> getTableColumn(String header, String propertyValue) {
    TableColumn<S, T> column = new TableColumn<>(header);

    column.setCellValueFactory(new PropertyValueFactory<>(propertyValue));

    return column;
  }
}
