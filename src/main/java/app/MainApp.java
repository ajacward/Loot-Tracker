package app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import app.model.Loot;
import app.model.LootListWrapper;
import app.model.LootType;
import app.viewcontroller.LootEditDialogController;
import app.viewcontroller.WelcomeLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

  public void loadLootDataFromFile(File file) {
    try {
      JAXBContext context = JAXBContext.newInstance(LootListWrapper.class);
      Unmarshaller um = context.createUnmarshaller();

      LootListWrapper wrapper = (LootListWrapper) um.unmarshal(file);

      Arrays.stream(LootType.values())
          .forEach(value -> lootData.put(value, FXCollections.observableArrayList()));

      wrapper.getLoots().stream().forEach(loot -> lootData.get(loot.getType()).add(loot));
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Could not load data");
      alert.setContentText("Could not load data from file:\n" + file.getPath());

      alert.showAndWait();
    }
  }

  public void saveLootDataToFile(File file) {
    try {
      JAXBContext context = JAXBContext.newInstance(LootListWrapper.class);
      Marshaller m = context.createMarshaller();
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      LootListWrapper wrapper = new LootListWrapper();
      List<Loot> loots =
          lootData.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
      wrapper.setLoots(loots);

      m.marshal(wrapper, file);
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Could not save data");
      alert.setContentText("Could not save data to file:\n" + file.getPath());

      alert.showAndWait();
    }
  }
}
