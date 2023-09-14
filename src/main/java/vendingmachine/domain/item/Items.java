package vendingmachine.domain.item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import vendingmachine.domain.money.Money;

public final class Items {

  private final List<Item> items;

  private Items(final List<Item> items) {
    this.items = items;
  }

  public static Items of(final List<Item> items) {
    return new Items(items);
  }

  public List<Item> findManyEqualOrCheaperThan(final Money money) {
    return items.stream()
        .filter(item -> item.getQuantity() > 0)
        .filter(item -> money.isEqualOrMoreThan(item.getPrice()))
        .collect(Collectors.toList());
  }

  public Item getItemByName(final String itemName) {
    return items.stream()
        .filter(item -> item.getName().equals(itemName))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("존재하지 않는 상품명입니다."));
  }

  public void decreaseQuantityByName(final String itemName) {
    final Optional<Item> foundItem = items.stream()
        .filter(item -> item.getName().equals(itemName))
        .findFirst();

    if (!foundItem.isPresent()) {
      throw new IllegalStateException("존재하지 않는 상품입니다.");
    }

    if (foundItem.get().getQuantity() < 1) {
      throw new IllegalStateException("재고가 없습니다.");
    }

    foundItem.get().decreaseQuantity();

  }

}
