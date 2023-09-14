package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.coin.RandomCoinGenerator;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.Items;
import vendingmachine.domain.money.Money;

public final class InputView {

  private final RandomCoinGenerator randomCoinGenerator;

  public InputView(final RandomCoinGenerator randomCoinGenerator) {
    this.randomCoinGenerator = randomCoinGenerator;
  }

  public Coins inputCoinsInMachine() {
    System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    final int amount = Integer.parseInt(readLine());
    final Money money = Money.of(amount);

    return randomCoinGenerator.generate(money);
  }

  public Items inputItemsInMachine() {
    System.out.println();
    System.out.println("상품명과 가격, 수량을 입력해 주세요.");

    final String itemStrings = readLine()
        .replace('[', ' ')
        .replace(']', ' ');

    final List<Item> itemList = toItemList(itemStrings);

    return Items.of(itemList);
  }

  private List<Item> toItemList(final String itemStrings) {
    final List<Item> items = new ArrayList<>();
    final String[] itemStringList = itemStrings.split(";");

    for (final String itemString : itemStringList) {
      final Item item = toItem(itemString);
      items.add(item);
    }
    return items;
  }

  private Item toItem(final String itemString) {
    final String[] split = itemString.trim().split(",");

    final String name = split[0];
    final Money price = Money.of(Integer.parseInt(split[1]));
    final int quantity = Integer.parseInt(split[2]);
    return Item.of(name, price, quantity);
  }

  public Money inputMoney() {
    System.out.println();
    System.out.println("투입 금액을 입력해 주세요.");
    try {
      final int amount = Integer.parseInt(readLine());
      return Money.of(amount);
    } catch (Exception e) {
      throw new IllegalArgumentException("금액은 숫자여야 합니다.");
    }
  }

  public String inputItemName(final int remainedAmount) {
    System.out.println();
    System.out.println("투입 금액: " + remainedAmount + "원");
    System.out.println("구매할 상품명을 입력해 주세요.");
    return readLine();
  }

  private String readLine() {
    return Console.readLine().trim();
  }
}