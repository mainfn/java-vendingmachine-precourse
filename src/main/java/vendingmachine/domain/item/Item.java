package vendingmachine.domain.item;

import vendingmachine.domain.money.Money;

public final class Item {

  private static final Money MIN_PRICE = Money.of(100);

  private static final Money PRICE_DIVISOR = Money.of(10);

  private final String name;

  private final Money price;

  private int quantity;

  private Item(
      final String name,
      final Money price,
      final int quantity
  ) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public static Item of(
      final String name,
      final Money price,
      final int quantity
  ) {
    validatePrice(price);
    validateQuantity(quantity);
    return new Item(name, price, quantity);
  }

  private static void validatePrice(final Money price) {
    if (!price.isEqualOrMoreThan(MIN_PRICE)) {
      throw new IllegalArgumentException("상품의 최소 가격은 100원 입니다.");
    }

    if (!price.isDivisibleBy(PRICE_DIVISOR)) {
      throw new IllegalArgumentException("상품의 가격은 반드시 10으로 나누어 떨어져야 합니다.");
    }
  }

  private static void validateQuantity(final int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("상품의 수량은 음수가 될 수 없습니다.");
    }
  }

  public void buy(final Money money) {
    validateQuantity(quantity--);
    money.decrease(price);
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getPrice() {
    return price;
  }

  public void decreaseQuantity() {
    quantity--;
  }
}
