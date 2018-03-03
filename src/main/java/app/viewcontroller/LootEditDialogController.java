package app.viewcontroller;

import app.model.Loot;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LootEditDialogController {
  @FXML
  private TextField nameField;
  @FXML
  private TextField quantityField;
  @FXML
  private TextField goldPieceField;
  @FXML
  private TextField notesField;

  private Stage dialogStage;
  private Loot loot;
  private boolean okClicked = false;

  @FXML
  private void initialize() {}

  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  public void setLoot(Loot loot) {
    this.loot = loot;
    nameField.setText(loot.getName());
    quantityField.setText(Integer.toString(loot.getQuantity()));
    goldPieceField.setText(Double.toString(loot.getGoldPieceValue()));
    notesField.setText(loot.getNotes());
  }

  public boolean isOkClicked() {
    return okClicked;
  }

  @FXML
  private void handleOk() {
    if (isInputValid()) {
      loot.setName(nameField.getText());
      loot.setQuantity(Integer.parseInt(quantityField.getText()));
      loot.setGoldPieceValue(Double.parseDouble(goldPieceField.getText()));
      loot.setNotes(notesField.getText());

      okClicked = true;
      dialogStage.close();
    }
  }

  @FXML
  private void handleCancel() {
    dialogStage.close();
  }

  private boolean isInputValid() {
    String errorMessage = "";

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
    alert.initOwner(dialogStage);
    alert.setTitle("Invalid Fields");
    alert.setHeaderText("Please correct invalid fields");
    alert.setContentText(errorMessage);

    alert.showAndWait();

    return false;
  }

}
