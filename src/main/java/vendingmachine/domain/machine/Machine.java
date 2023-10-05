package vendingmachine.domain.machine;

import java.util.Optional;
import vendingmachine.domain.coin.Changes;
import vendingmachine.domain.coin.Money;
import vendingmachine.domain.coin.ReturnedChanges;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.Items;

public final class Machine {

  private final Changes changes;
  private final Items items;
  private final Money inputMoney;

  public Machine(
      final Changes changes,
      final Items items,
      final Money money
  ) {
    this.changes = changes;
    this.items = items;
    this.inputMoney = money;
  }

  public boolean canPurchaseAny() {
    // 1. 잔돈 여부
    // 2. 가장 저렴한 제품 < 투입 금액 체크
    final Optional<Item> cheapestItem = items.findCheapestItem();

    if (cheapestItem.isEmpty()) {
      return false;
    }

    return !changes.isEmpty() &&
        inputMoney.hasEqualOrMoreAmountThan(cheapestItem.get().getPrice());
  }

  public Money purchase(final String itemName) {
    final Item item = items.getItemByName(itemName);
    item.purchase(inputMoney);

    return inputMoney;
  }

  public ReturnedChanges returnChanges() {
    return changes.returnChanges(inputMoney);
  }

  public Money getInputMoney() {
    return inputMoney;
  }
}
