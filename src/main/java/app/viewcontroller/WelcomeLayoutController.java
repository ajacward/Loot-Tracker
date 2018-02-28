package app.viewcontroller;

import app.MainApp;
import javafx.fxml.FXML;

public class WelcomeLayoutController {
  private MainApp mainApp;

  @FXML
  private void handleCreateNewRecord() {}

  @FXML
  private void handleLoadRecord() {}

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }
}
