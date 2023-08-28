package vendingmachine.controller;

import vendingmachine.domain.CoinWallet;
import vendingmachine.domain.Items;
import vendingmachine.domain.Machine;
import vendingmachine.domain.Money;
import vendingmachine.view.MachineView;

public final class MachineController {

  private final InputManager inputManager;
  private final MachineView machineView;
  private final Machine machine;

  public MachineController(
      final InputManager inputManager,
      final MachineView machineView,
      final Machine machine
  ) {
    this.inputManager = inputManager;
    this.machineView = machineView;
    this.machine = machine;
  }

  // 1. 자판기에 재고 채우기
  public void run() {
    fillCoins();
    fillItems();

    insertMoney();

    purchaseItemUntilAvailable();
  }

  // 1-1. 동전 채우기 및 자판기가 보유한 동전 목록 출력
  private void fillCoins() {
    final CoinWallet coinWallet = inputManager.inputCoinWallet();
    machine.putCoinWallet(coinWallet);
    machineView.printReservedCoinsInMachine(coinWallet);
  }

  // 1-2. 상품 채우기
  private void fillItems() {
    final Items items = inputManager.inputItems();
    machine.putItems(items);
    System.out.println();
  }

  // 2. 상품 구매하기
  private void insertMoney() {
    final Money money = inputManager.inputMoney();
    machine.insertMoney(money);
  }

  // 2-1. 구매 가능 시 계속
  private void purchaseItemUntilAvailable() {
    machineView.printRemainedMoney(machine.getRemainedMoney());
    if (machine.cannotPurchaseNothing()) {
      getChangeCoins();
      return;
    }
    final String itemName = inputManager.inputItemName();
    machine.purchaseItem(itemName);

    purchaseItemUntilAvailable();
  }

  // 2-2. 구매 불가 시 잔돈 출력 후 종료
  private void getChangeCoins() {
    final CoinWallet coinWallet = machine.returnChangeCoins();
    machineView.printChangeCoins(coinWallet);
  }
}
