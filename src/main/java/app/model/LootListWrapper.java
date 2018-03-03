package app.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loots")
public class LootListWrapper {
  private List<Loot> loots;

  @XmlElement(name = "loot")
  public List<Loot> getLoots() {
    return loots;
  }

  public void setLoots(List<Loot> loots) {
    this.loots = loots;
  }

}
