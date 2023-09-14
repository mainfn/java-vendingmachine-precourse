package vendingmachine.domain.machine;

import vendingmachine.domain.coin.Changes;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Item;
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

  public int getRemainedAmount() {
    return remainedMoney.getAmount();
  }

  // 1. 돈 투입
  public void insertMoney(final Money money) {
    remainedMoney.increase(money);
  }

  // 2. 상품 구매 가능 여부 확인
  public boolean canBuyAny() {
    return !items.findManyEqualOrCheaperThan(remainedMoney).isEmpty();
  }

  // 3. 상품 구매하기
  public void buy(final String itemName) {
    // 상품을 찾아옴
    final Item item = items.getItemByName(itemName);
    // Item의 재고를 1 감소 시키고, 상품 가격만큼
    // 자판기 내에 투입된 잔액을 감소시킴
    item.buy(remainedMoney);
  }

  // 4. 잔돈 반환하기
  public Changes getChanges() {
    return coins.exchangeAll(remainedMoney);
  }
}
