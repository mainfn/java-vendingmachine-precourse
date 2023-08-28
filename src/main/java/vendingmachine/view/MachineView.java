package vendingmachine.view;

import vendingmachine.domain.CoinWallet;
import vendingmachine.domain.Money;

public final class MachineView {

  // 1. 보유한 동전 출력
  public void printReservedCoinsInMachine(final CoinWallet coinWallet) {
    System.out.println("자판기가 보유한 동전");
    System.out.println(coinWallet.toString());
    System.out.println();
  }

  // 2. 잔돈 출력
  public void printChangeCoins(final CoinWallet coinWallet) {
    System.out.println("잔돈");
    System.out.println(coinWallet.toString());
  }

  public void printRemainedMoney(final Money remainedMoney) {
    System.out.printf("투입 금액: %d원\n", remainedMoney.getAmount());
  }
}
