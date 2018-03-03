package app.viewcontroller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import app.MainApp;
import app.model.Loot;
import app.model.LootType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WorkLayoutController {
  @FXML
  private ComboBox<LootType> typeComboBox;
  @FXML
  private TextField nameField;
  @FXML
  private TextField quantityField;
  @FXML
  private TextField goldPieceField;
  @FXML
  private TextField notesField;

  @FXML
  private Button addButton;

  @FXML
  private VBox tableEntry;

  private Map<LootType, TableView<Loot>> tableMap;

  private MainApp mainApp;

  public WorkLayoutController() {}

  @FXML
  public void initialize() {
    tableMap = new HashMap<>();
    Arrays.stream(LootType.values()).forEach(value -> tableMap.put(value, makeTable(value)));

    typeComboBox.getItems().setAll(LootType.values());
    quantityField.setText("1");
  }

  private TableView<Loot> makeTable(LootType type) {
    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 10, 10, 10));

    final Label label = new Label(type.toString());
    label.setFont(new Font("Arial", 20));

    final ButtonBar buttonBar = new ButtonBar();

    TableView<Loot> table = new TableView<>();
    table.setMaxHeight(200);
    table.setPlaceholder(new Label("No loot"));

    TableColumn<Loot, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setResizable(false);
    nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    TableColumn<Loot, Number> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setResizable(false);
    quantityColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());

    TableColumn<Loot, Number> goldPieceColumn = new TableColumn<>("Gold Pieces");
    goldPieceColumn.setResizable(false);
    goldPieceColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    goldPieceColumn.setCellValueFactory(cellData -> cellData.getValue().goldPieceValueProperty());

    TableColumn<Loot, String> notesColumn = new TableColumn<>("Notes");
    notesColumn.setResizable(false);
    notesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
    notesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());

    table.getColumns().addAll(nameColumn, quantityColumn, goldPieceColumn, notesColumn);

    final Button editButton = new Button("Edit");

    editButton.setOnAction(e -> {
      Loot selectedLoot = table.getSelectionModel().getSelectedItem();

      if (selectedLoot != null) {
        mainApp.showLootEditDialog(selectedLoot);
      } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Loot Selected");
        alert.setContentText("Please select a loot in the table.");

        alert.showAndWait();
      }
    });

    final Button deleteButton = new Button("Delete");

    deleteButton.setOnAction(e -> {
      Loot selectedLoot = table.getSelectionModel().getSelectedItem();
      table.getItems().remove(selectedLoot);
    });

    buttonBar.getButtons().addAll(editButton, deleteButton);

    vbox.getChildren().addAll(label, buttonBar, table);

    tableEntry.getChildren().add(vbox);

    return table;
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;

    mainApp.getLootData().forEach((key, value) -> {
      tableMap.get(key).setItems(value);
    });
  }

  @FXML
  private void handleAddLoot() {
    if (isInputValid()) {
      Loot tempLoot = new Loot(typeComboBox.getValue(), nameField.getText(),
          Integer.parseInt(quantityField.getText()),
          Double.parseDouble(goldPieceField.getText().isEmpty() ? "0" : goldPieceField.getText()),
          notesField.getText());

      mainApp.getLootData().get(typeComboBox.getValue()).add(tempLoot);

      clearFields();
    }
  }

  private boolean isInputValid() {
    String errorMessage = "";

    if (typeComboBox.getValue() == null) {
      errorMessage += "No valid loot type!\n";
    }

    if (nameField.getText() == null || nameField.getText().length() == 0) {
      errorMessage += "No valid loot name!\n";
    }

    if (quantityField.getText() == null || quantityField.getText().length() == 0) {
      errorMessage += "No valid quantity!\n";
    }

    if (errorMessage.length() == 0) {
      return true;
    }

    Alert alert = new Alert(AlertType.ERROR);
    alert.initOwner(mainApp.getPrimaryStage());
    alert.setTitle("Invalid Fields");
    alert.setHeaderText("Please correct invalid fields");
    alert.setContentText(errorMessage);

    alert.showAndWait();

    return false;
  }

  private void clearFields() {
    typeComboBox.setValue(null);
    nameField.clear();
    quantityField.setText("1");
    goldPieceField.clear();
    notesField.clear();
  }

}
