package app.viewcontroller;

import app.MainApp;
import app.model.Loot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WorkLayoutController {
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

  @FXML
  private Label nameLabel;
  @FXML
  private Label quanityLabel;
  @FXML
  private Label goldPieceValueLabel;
  @FXML
  private Label notesLabel;

  private MainApp mainApp;

  public WorkLayoutController() {}

  @FXML
  public void initialize() {
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
    goldPieceValueColumn
        .setCellValueFactory(cellData -> cellData.getValue().goldPieceValueProperty());
    notesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;

    lootTable.setItems(mainApp.getLootData());
  }
}
