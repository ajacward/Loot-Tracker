package app.viewcontroller;

import java.io.IOException;
import app.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class WelcomeLayoutController {
  private MainApp mainApp;

  @FXML
  private void handleCreateNewRecord() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("viewcontroller/WorkLayout.fxml"));
      VBox workLayout = (VBox) loader.load();

      Scene workScene = new Scene(workLayout);
      mainApp.getPrimaryStage().setScene(workScene);

      WorkLayoutController controller = loader.getController();
      controller.setMainApp(mainApp);

      mainApp.getPrimaryStage().show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void handleLoadRecord() {}

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }
}
