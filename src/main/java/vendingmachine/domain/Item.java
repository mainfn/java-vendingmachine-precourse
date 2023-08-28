package vendingmachine.domain;

import java.util.regex.Pattern;

public final class Item {

  private final String name;
  private final int price;
  private int quantity;

  private Item(
      final String name,
      final int price,
      final int quantity
  ) {
    validateItem(name, price, quantity);
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public static Item from(String input) {

    panicIfNotValidatedInput(input);

    input = input.substring(1, input.length() - 1);
    final String[] fields = input.split(",");
    final String name = fields[0];
    final int price = Integer.parseInt(fields[1]);
    final int quantity = Integer.parseInt(fields[2]);

    return new Item(name, price, quantity);
  }

  private static void panicIfNotValidatedInput(final String input) {
    System.out.println("input = " + input);
    final boolean isInvalidInput = !Pattern.matches("\\[\\w+,\\d+,\\d+\\]", input);

    if (isInvalidInput) {
      throw new IllegalArgumentException("상품 입력 양식이 잘못되었습니다.");
    }
  }


  private static void validateItem(
      final String name,
      final int price,
      final int quantity
  ) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("유효하지 않은 상품명");
    }
    if (price % 10 != 0 || price < 100) {
      throw new IllegalArgumentException("상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.");
    }

    if (quantity < 0) {
      throw new IllegalArgumentException("수량은 0 이하가 될 수 없음");
    }
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void decreaseQuantity() {
    quantity--;
  }
}
