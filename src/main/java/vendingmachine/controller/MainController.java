package vendingmachine.controller;

import vendingmachine.domain.coin.Changes;
import vendingmachine.domain.coin.CoinExchange;
import vendingmachine.domain.coin.Money;
import vendingmachine.domain.coin.ReturnedChanges;
import vendingmachine.domain.item.Items;
import vendingmachine.domain.machine.Machine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class MainController {

  private final OutputView outputView;
  private final InputView inputView;

  private final CoinExchange coinExchange;

  public MainController(
      final OutputView outputView,
      final InputView inputView,
      final CoinExchange coinExchange
  ) {
    this.outputView = outputView;
    this.inputView = inputView;
    this.coinExchange = coinExchange;
  }

  public void run() {
    // 자판기 생성
    final Machine machine = createMachine();
    // 상품 구매
    final ReturnedChanges changes = purchase(machine);
    // 잔돈 출력
    outputView.printInputMoney(machine.getInputMoney());
    outputView.printReturnedChanges(changes);
  }

  private Machine createMachine() {
    // 1. 자판기 보유 금액 입력 및 잔돈 변환
    final Money money = inputView.inputMoneyInMachine();
    final Changes changes = coinExchange.exchangeUntilOutOf(money);

    // 2. 자판기 보유 동전 출력
    outputView.printChanges(changes);
    // 3. 자판기 보유 상품 입력
    final Items items = inputView.inputItemsInMachine();
    // 4. 금액 투입
    final Money inputMoney = inputView.inputMoney();

    return new Machine(changes, items, inputMoney);
  }

  private ReturnedChanges purchase(final Machine machine) {
    // 1. 구매 가능할 때까지 상품 입력 및 구매
    while (machine.canPurchaseAny()) {
      outputView.printInputMoney(machine.getInputMoney());
      final String itemName = inputView.inputPurchasingItemName();
      machine.purchase(itemName);
    }
    return machine.returnChanges();
  }
}
