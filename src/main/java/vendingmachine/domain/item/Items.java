package vendingmachine.domain.item;

import java.util.List;

public final class Items {

  private final List<Item> items;

  private Items(final List<Item> items) {
    this.items = items;
  }

  public static Items of(final List<Item> items) {
    return new Items(items);
  }

}
