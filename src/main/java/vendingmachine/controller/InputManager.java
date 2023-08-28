package vendingmachine.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.CoinWallet;
import vendingmachine.domain.Item;
import vendingmachine.domain.Items;
import vendingmachine.domain.Money;
import vendingmachine.util.RandomCoinWalletGenerator;

public final class InputManager {

  private final RandomCoinWalletGenerator randomCoinWalletGenerator;

  public InputManager(final RandomCoinWalletGenerator randomCoinWalletGenerator) {
    this.randomCoinWalletGenerator = randomCoinWalletGenerator;
  }

  private static String readLine() {
    return Console.readLine().trim();
  }

  // 1. 금액 입력 받아서 동전으로 변환하여 반환
  public CoinWallet inputCoinWallet() {
    try {
      System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
      final Integer amount = Integer.parseInt(readLine());
      return randomCoinWalletGenerator.generateCoinWallet(amount);
    } catch (final Exception e) {
      if (e instanceof NumberFormatException) {
        throw new IllegalArgumentException("금액은 숫자여야 합니다.");
      }
      throw e;
    }
  }

  // 2. 상품명, 가격, 수량 입력 받아서 변환 및 반환
  public Items inputItems() {
    System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    final List<String> itemStrs = Arrays.stream(readLine().split(";"))
        .collect(Collectors.toList());

    final List<Item> items = itemStrs.stream()
        .map(Item::from)
        .collect(Collectors.toList());

    return Items.of(items);
  }

  // 3. 금액 입력 받아서 숫자로 변환 및 반환
  public Money inputMoney() {
    System.out.println("투입 금액을 입력해 주세요.");
    final int amount = Integer.parseInt(readLine().trim());
    System.out.println();
    return Money.of(amount);
  }

  // 4. 구매할 상품명 입력
  public String inputItemName() {
    System.out.println("구매할 상품명을 입력해 주세요.");
    final String itemName = readLine();
    System.out.println();
    return itemName;
  }
}
