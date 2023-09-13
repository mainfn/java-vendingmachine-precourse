package vendingmachine.domain.machine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Items;
import vendingmachine.domain.money.Money;

public final class Machine {

  private final Coins coins;
  private final Items items;

  private final Money remainedMoney = Money.of(0);

  private Machine(
      final Coins coins,
      final Items items
  ) {
    this.coins = coins;
    this.items = items;
  }

  public static Machine of(
      final Coins coins,
      final Items items
  ) {
    return new Machine(coins, items);
  }

  // 1. 돈 투입
  public void insertMoney(final Money money) {
    remainedMoney.increase(money);
  }

  // 2. 상품 구매 가능 여부 확인
  public boolean canBuyAny() {
    throw new NotImplementedException();
  }

  // 3. 상품 구매하기
  public void buy(final String itemName) {
    throw new NotImplementedException();
  }

  // 4. 잔돈 반환하기
  public Coins getChanges() {
    throw new NotImplementedException();
  }
}
