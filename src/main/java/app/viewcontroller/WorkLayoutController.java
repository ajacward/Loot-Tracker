package app.viewcontroller;

import java.io.File;
import app.MainApp;
import app.model.Loot;
import app.model.LootType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

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

  private MainApp mainApp;

  public WorkLayoutController() {}

  @FXML
  public void initialize() {
    typeComboBox.getItems().setAll(LootType.values());
    quantityField.setText("1");
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
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

  @FXML
  private void handleSaveAs() {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter
    FileChooser.ExtensionFilter extFilter =
        new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

    if (file != null) {
      // Make sure it has the correct extension
      if (!file.getPath().endsWith(".xml")) {
        file = new File(file.getPath() + ".xml");
      }
      mainApp.saveLootDataToFile(file);
    }
  }

}
