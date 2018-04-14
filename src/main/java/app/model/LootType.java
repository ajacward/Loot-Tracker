package app.model;

public enum LootType {
  ARMOR, ARTIFACT, CLOTHING, CURSED, GEAR, INTELLIGENT, MOUNT, POTION, PROVISION, RING, ROD, SCROLL, SPECIAL, STAVE, STORY_ITEM, TOOL, TRADE_GOOD, TRANSPORT, WAND, WEAPON, WONDROUS;

  @Override
  public String toString() {
    switch (this) {
      case TRADE_GOOD:
        return "Trade Good";
      case WEAPON:
        return "Weapon";
      case ARMOR:
        return "Armor";
      case GEAR:
        return "Gear";
      case SPECIAL:
        return "Special";
      case TOOL:
        return "Tool";
      case CLOTHING:
        return "Clothing";
      case PROVISION:
        return "Provision";
      case MOUNT:
        return "Mount";
      case TRANSPORT:
        return "Transport";
      case POTION:
        return "Potion";
      case RING:
        return "Ring";
      case ROD:
        return "Rod";
      case SCROLL:
        return "Scroll";
      case STAVE:
        return "Stave";
      case WAND:
        return "Wand";
      case WONDROUS:
        return "Wondrous";
      case INTELLIGENT:
        return "Intelligent";
      case CURSED:
        return "Cursed";
      case ARTIFACT:
        return "Artifact";
      case STORY_ITEM:
        return "Story Item";
      default:
        return "Unspecified";
    }
  }
}
