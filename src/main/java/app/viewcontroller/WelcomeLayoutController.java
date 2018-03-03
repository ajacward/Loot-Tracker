package app.viewcontroller;

import java.io.File;
import java.io.IOException;
import app.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

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
  private void handleLoadRecord() {
    handleOpen();

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

  private void handleOpen() {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter
    FileChooser.ExtensionFilter extFilter =
        new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show open file dialog
    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

    if (file != null) {
      mainApp.loadLootDataFromFile(file);
    }
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }
}
