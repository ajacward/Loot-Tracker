package app;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import app.model.Loot;
import app.model.LootType;
import app.viewcontroller.LootEditDialogController;
import app.viewcontroller.WelcomeLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
  private Stage primaryStage;
  private Map<LootType, ObservableList<Loot>> lootData;

  public MainApp() {
    lootData = new HashMap<>();

    Arrays.stream(LootType.values())
        .forEach(value -> lootData.put(value, FXCollections.observableArrayList()));

    lootData.get(LootType.WEAPON)
        .add(new Loot(LootType.WEAPON, "composite short bow", 1, 30, "strength rating 2"));
    lootData.get(LootType.WEAPON).add(new Loot(LootType.WEAPON, "longsword", 1, 50, ""));
    lootData.get(LootType.WEAPON).add(new Loot(LootType.WEAPON, "dagger", 3, 2, "masterwork"));
    lootData.get(LootType.WEAPON).add(new Loot(LootType.WEAPON, "arrow", 20, 1, ""));
  }

  public Map<LootType, ObservableList<Loot>> getLootData() {
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

  public boolean showLootEditDialog(Loot loot) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("viewcontroller/LootEditDialog.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Edit Loot");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      LootEditDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setLoot(loot);

      dialogStage.showAndWait();

      return controller.isOkClicked();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }
}
