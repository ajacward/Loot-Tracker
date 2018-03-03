package app.model;

public enum LootType {
  TRADE_GOOD, WEAPON, ARMOR, GEAR, SPECIAL, TOOL, CLOTHING, PROVISION, MOUNT, TRANSPORT, POTION, RING, ROD, SCROLL, STAVE, WAND, WONDROUS, INTELLIGENT, CURSED, ARTIFACT, STORY_ITEM;

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
