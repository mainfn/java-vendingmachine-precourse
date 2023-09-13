package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.Items;
import vendingmachine.domain.money.Money;

public final class InputView {

  public Coins inputCoinsInMachine() {
    System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    final int amount = Integer.parseInt(readLine());

    // TODO:
    // 자판기에 넣을 잔돈을 Coins로 변환 및 반환
    throw new NotImplementedException();
  }

  public Items inputItemsInMachine() {
    System.out.println("상품명과 가격, 수량을 입력해 주세요.");

    final List<String> itemStrings = Arrays.stream(readLine().split(";"))
        .collect(Collectors.toList());

    // TODO:
    // Items 형태로 변환 및 반환
    throw new NotImplementedException();
  }

  public Money inputMoney() {
    System.out.println("투입 금액을 입력해 주세요.");
    final int amount = Integer.parseInt(readLine());

    // TODO:
    // Money로 변환 및 반환
    throw new NotImplementedException();
  }

  public Item inputItemToBuy() {
    final String itemName = readLine();

    // TODO:
    // Item으로 변환 및 반환
    throw new NotImplementedException();
  }

  private String readLine() {
    return Console.readLine().trim();
  }
}