package app.viewcontroller;

import app.MainApp;
import app.model.Loot;
import app.model.LootType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
  private TableView<Loot> lootTable;
  @FXML
  private TableColumn<Loot, String> nameColumn;
  @FXML
  private TableColumn<Loot, Number> quantityColumn;
  @FXML
  private TableColumn<Loot, Number> goldPieceValueColumn;
  @FXML
  private TableColumn<Loot, String> notesColumn;

  private MainApp mainApp;

  public WorkLayoutController() {}

  @FXML
  public void initialize() {
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
    goldPieceValueColumn
        .setCellValueFactory(cellData -> cellData.getValue().goldPieceValueProperty());
    notesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());

    typeComboBox.getItems().setAll(LootType.values());
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;

    lootTable.setItems(mainApp.getLootData());
  }

  @FXML
  private void handleAddLoot() {
    if (isInputValid()) {
      Loot tempLoot = new Loot(typeComboBox.getValue(), nameField.getText(),
          Integer.parseInt(quantityField.getText()), Double.parseDouble(goldPieceField.getText()),
          notesField.getText());

      mainApp.getLootData().add(tempLoot);

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
    quantityField.clear();
    goldPieceField.clear();
    notesField.clear();
  }

}
