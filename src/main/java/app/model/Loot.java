package app.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loot {
  private final ObjectProperty<LootType> type;
  private final StringProperty name;
  private final IntegerProperty quantity;
  private final DoubleProperty goldPieceValue;
  private final StringProperty notes;

  // This is just for JAXB
  public Loot() {
    this(null, null, 0, 0, null);
  }

  public Loot(LootType type, String name, int quantity, double goldPieces, String notes) {
    this.type = new SimpleObjectProperty<>(type);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.goldPieceValue = new SimpleDoubleProperty(goldPieces);
    this.notes = new SimpleStringProperty(notes);
  }

  public LootType getType() {
    return type.get();
  }

  public void setType(LootType type) {
    this.type.set(type);
  }

  public ObjectProperty<LootType> typeProperty() {
    return type;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public int getQuantity() {
    return quantity.get();
  }

  public void setQuantity(int quantity) {
    this.quantity.set(quantity);
  }

  public IntegerProperty quantityProperty() {
    return quantity;
  }

  public double getGoldPieceValue() {
    return goldPieceValue.get();
  }

  public void setGoldPieceValue(double goldPieceValue) {
    this.goldPieceValue.set(goldPieceValue);
  }

  public DoubleProperty goldPieceValueProperty() {
    return goldPieceValue;
  }

  public String getNotes() {
    return notes.get();
  }

  public void setNotes(String notes) {
    this.notes.set(notes);
  }

  public StringProperty notesProperty() {
    return notes;
  }

}
