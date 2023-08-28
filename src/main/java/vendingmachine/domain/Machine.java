package vendingmachine.domain;

import java.util.List;
import java.util.Optional;

public final class Machine {

  private final Money remainedMoney = Money.empty();
  private CoinWallet coinWallet = CoinWallet.empty();
  private Items items = Items.empty();

  public Machine() {
  }

  public Money getRemainedMoney() {
    return remainedMoney;
  }

  // 1. 동전 채우기
  public void putCoinWallet(final CoinWallet coinWallet) {
    this.coinWallet = coinWallet;
  }

  // 2. 상품 채우기
  public void putItems(final Items items) {
    this.items = items;
  }

  // 3. 돈 투입
  public void insertMoney(final Money money) {
    remainedMoney.increase(money);
  }

  private boolean isSoldOut(final Item item) {
    return item.getQuantity() == 0;
  }

  private boolean notEnoughMoneyFor(final Item item) {
    return !remainedMoney.isEqualOrMoreThan(item.getPrice());
  }

  public boolean cannotPurchaseNothing() {
    final List<Item> itemsAtLeastOneQuantity = items.getItemsAtLeastOneQuantity();

    final boolean isSoldOut = itemsAtLeastOneQuantity.isEmpty();
    final boolean hasEnoughMoney = itemsAtLeastOneQuantity.stream()
        .anyMatch(item -> remainedMoney.isEqualOrMoreThan(item.getPrice()));

    return isSoldOut || !hasEnoughMoney;
  }

  // 4. 물건 구매
  public void purchaseItem(final String itemName) {
    // 1. 물건 찾아옴
    final Optional<Item> item = items.findByName(itemName);
    // 존재하지 않는 물건
    if (!item.isPresent()) {
      throw new IllegalArgumentException("물건을 찾을 수 없습니다.");
    }
    if (isSoldOut(item.get())) {
      throw new IllegalArgumentException("품절입니다");
    }
    if (notEnoughMoneyFor(item.get())) {
      throw new IllegalArgumentException("잔액이 부족합니다");
    }

    // 3. 물건 갯수 줄임
    items.decreaseQuantityOf(itemName);
  }

  // 5. 잔돈 반환
  public CoinWallet returnChangeCoins() {
    return coinWallet.take(remainedMoney);
  }
}
