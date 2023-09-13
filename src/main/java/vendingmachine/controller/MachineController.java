package vendingmachine.controller;

import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Items;
import vendingmachine.domain.machine.Machine;
import vendingmachine.domain.money.Money;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class MachineController {

  private final InputView inputView;
  private final OutputView outputView;
  private final Machine machine;

  public MachineController(
      final InputView inputView,
      final OutputView outputView,
      final Machine machine
  ) {
    this.inputView = inputView;
    this.outputView = outputView;
    this.machine = machine;
  }

  public void start() {
    // 입력
    final Machine machine = createMachine();

    // 실행
    final Coins changes = buyItems(machine);

    // 출력
    outputView.printChanges(changes);
  }

  private Machine createMachine() {
    // 자판기가 보유한 금액 입력 및 잔돈으로 변환
    final Coins coins = inputView.inputCoinsInMachine();

    // 보유 동전 현황 출력
    outputView.printCoinsInMachine(coins);

    // 상품 입력 및 변환
    final Items items = inputView.inputItemsInMachine();

    // 자판기 생성 및 반환
    return Machine.of(coins, items);
  }

  private Coins buyItems(final Machine machine) {
    // 상품 구매를 위해 지폐 입력 및 투입
    final Money money = inputView.inputMoney();
    machine.insertMoney(money);

    // 더 이상 구매할 수 없을 때까지 실행
    while (machine.canBuyAny()) {
      // 구매할 아이템명 입력
      final String itemName = inputView.inputItemName();
      machine.buy(itemName);
    }

    // 잔돈 반환
    return machine.getChanges();
  }
}
