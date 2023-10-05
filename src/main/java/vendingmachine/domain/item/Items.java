package vendingmachine.domain.item;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Items {

  private final List<Item> items;

  private Items(final List<Item> items) {
    this.items = items;
  }

  public static Items of(final List<Item> items) {
    return new Items(items);
  }

  public void addItems(final List<Item> newItems) {
    this.items.addAll(newItems);
  }

  public Optional<Item> findCheapestItem() {
    return findInStockItems().stream()
        .max(Comparator.comparingInt(Item::getPrice));
  }

  private List<Item> findInStockItems() {
    return items.stream()
        .filter(Item::hasStocks)
        .collect(Collectors.toList());
  }

  public Item getItemByName(final String itemName) {
    return items.stream()
        .filter(i -> i.getName().equals(itemName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품명입니다."));
  }
}
