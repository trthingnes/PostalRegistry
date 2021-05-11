package edu.ntnu.tobiasth.idatt2001.postalregistry.util;

import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Builder for {@link TableColumn} objects.
 * @param <S> Table content type.
 * @param <T> Cell content type.
 *
 * @author trthingnes
 */
public class TableColumnBuilder<S, T> {
  private final TableColumn<S, T> tableColumn = new TableColumn<>();

  /**
   * Sets the header text of the table column.
   * @param text Header text.
   * @return Builder object.
   */
  public TableColumnBuilder<S, T> setText(String text) {
    tableColumn.setText(text);
    return this;
  }

  /**
   * Sets the cell value factory to on with the given property name.
   * @param property Property name.
   * @return Builder object.
   */
  public TableColumnBuilder<S, T> setCellPropertyValue(String property) {
    tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
    return this;
  }

  /**
   * Sets the min width of the column to the preferred width of the content.
   * @return Builder object.
   */
  public TableColumnBuilder<S, T> setFitContentProperty() {
    tableColumn.setMinWidth(tableColumn.getPrefWidth());
    return this;
  }

  /**
   * Adds nested columns to the column.
   * @param columns Nested columns to add.
   * @return Builder object.
   */
  public TableColumnBuilder<S, T> addNestedColumns(List<TableColumn<S, T>> columns) {
    tableColumn.getColumns().addAll(columns);
    return this;
  }

  /**
   * Returns the built {@link TableColumn} object.
   * @return Table column object.
   */
  public TableColumn<S, T> build() {
    return tableColumn;
  }
}
