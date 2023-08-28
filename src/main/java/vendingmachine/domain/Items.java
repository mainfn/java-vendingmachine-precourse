package vendingmachine.domain;

import java.util.ArrayList;
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


  public static Items empty() {
    return new Items(new ArrayList<>());
  }

  public Optional<Item> findByName(final String itemName) {
    return items.stream()
        .filter(item -> item.getName().equals(itemName))
        .findFirst();
  }

  public List<Item> getItemsAtLeastOneQuantity() {
    return items.stream()
        .filter(item -> item.getQuantity() > 0)
        .collect(Collectors.toList());
  }

  public boolean hasNoQuantity() {
    return getItemsAtLeastOneQuantity().isEmpty();
  }

  private Item mustGetByName(final String itemName) {
    final Optional<Item> item = findByName(itemName);
    if (!item.isPresent()) {
      throw new IllegalArgumentException("존재하지 않는 상품");
    }
    return item.get();
  }

  public void decreaseQuantityOf(final String itemName) {
    final Item item = mustGetByName(itemName);
    item.decreaseQuantity();
  }
}

