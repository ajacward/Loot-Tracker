package app;

import java.io.IOException;
import app.viewcontroller.WelcomeLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

  private Stage primaryStage;
  private VBox welcomeLayout;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.primaryStage = stage;
    this.primaryStage.setTitle("Loot Tracker");

    initWelcomeLayout();
  }

  public void initWelcomeLayout() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("viewcontroller/WelcomeLayout.fxml"));
      welcomeLayout = (VBox) loader.load();

      Scene scene = new Scene(welcomeLayout);
      primaryStage.setScene(scene);

      WelcomeLayoutController controller = loader.getController();
      controller.setMainApp(this);

      primaryStage.show();
      primaryStage.setResizable(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }
}
