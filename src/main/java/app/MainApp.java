package app;

import java.io.IOException;
import app.model.Loot;
import app.model.LootType;
import app.viewcontroller.WelcomeLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
  private Stage primaryStage;
  private ObservableList<Loot> lootData = FXCollections.observableArrayList();

  public MainApp() {
    lootData.add(new Loot(LootType.WEAPON, "composite short bow", 1, 30, "strength rating 2"));
    lootData.add(new Loot(LootType.WEAPON, "longsword", 1, 50, ""));
    lootData.add(new Loot(LootType.WEAPON, "dagger", 3, 2, "masterwork"));
    lootData.add(new Loot(LootType.WEAPON, "arrow", 20, 1, ""));
  }

  public ObservableList<Loot> getLootData() {
    return lootData;
  }

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
      VBox welcomeLayout = (VBox) loader.load();

      Scene welcomeScene = new Scene(welcomeLayout);
      primaryStage.setScene(welcomeScene);

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
