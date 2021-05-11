package edu.ntnu.tobiasth.idatt2001.postalregistry.util;

import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableColumnBuilder<S, T> {
  private final TableColumn<S, T> tableColumn = new TableColumn<>();

  public TableColumnBuilder<S, T> setText(String text) {
    tableColumn.setText(text);
    return this;
  }

  public TableColumnBuilder<S, T> setCellPropertyValue(String property) {
    tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
    return this;
  }

  public TableColumnBuilder<S, T> setFitContentProperty() {
    tableColumn.setMinWidth(tableColumn.getPrefWidth());
    return this;
  }

  public TableColumnBuilder<S, T> addNestedColumns(List<TableColumn<S, T>> columns) {
    tableColumn.getColumns().addAll(columns);
    return this;
  }

  public TableColumn<S, T> build() {
    return tableColumn;
  }
}
