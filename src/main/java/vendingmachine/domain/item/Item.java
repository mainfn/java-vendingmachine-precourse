package vendingmachine.domain.item;

import vendingmachine.domain.coin.Money;

public final class Item {

  private final String name;
  private final int price;

  private int quantity;

  private Item(
      final String name,
      final int price,
      final int quantity
  ) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public static Item from(final String input) {
    final String[] split = input.substring(1, input.length() - 1).split(",");

    final String name = split[0];
    validatePrice(split[1]);
    validateQuantity(split[2]);

    return new Item(name, Integer.parseInt(split[1]), Integer.parseInt(split[2]));
  }

  private static void validatePrice(final String priceString) {
    try {
      Integer.parseInt(priceString);
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("금액은 숫자여야 합니다.");
    }
  }

  private static void validateQuantity(final String quantityString) {
    try {
      Integer.parseInt(quantityString);
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("상품 수량은 숫자여야 합니다.");
    }
  }

  public boolean hasStocks() {
    return quantity > 0;
  }

  public int getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void purchase(final Money money) {
    if (!money.hasEqualOrMoreAmountThan(price)) {
      throw new IllegalArgumentException("잔액이 부족하여 상품을 결제하는데 실패했습니다.");
    }
    money.decrease(price);
    validateStocks();
    quantity--;
  }

  private void validateStocks() {
    if (quantity <= 0) {
      throw new IllegalArgumentException("재고가 부족합니다.");
    }
  }
}
