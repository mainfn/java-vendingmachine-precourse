package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.coin.Money;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.Items;

public final class InputView {

  private final InputValidator inputValidator;

  public InputView(final InputValidator inputValidator) {
    this.inputValidator = inputValidator;
  }

  public Money inputMoneyInMachine() {
    try {
      System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
      return Money.of(Integer.parseInt(readLine()));
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("금액은 숫자만 입력 가능합니다.");
    }
  }

  public Items inputItemsInMachine() {
    System.out.println();
    System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    final String input = readLine();
    inputValidator.validateItemsInput(input);

    final List<Item> items = new ArrayList();
    for (final String itemString : input.split(";")) {
      items.add(Item.from(itemString));
    }

    return Items.of(items);
  }

  public Money inputMoney() {
    try {
      System.out.println();
      System.out.println("투입 금액을 입력해 주세요.");
      return Money.of(Integer.parseInt(readLine()));
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("투입 금액은 숫자만 입력 가능합니다.");
    }
  }

  private String readLine() {
    return Console.readLine().trim();
  }

  public String inputPurchasingItemName() {
    System.out.println("구매할 상품명을 입력해 주세요.");
    return readLine();
  }
}
