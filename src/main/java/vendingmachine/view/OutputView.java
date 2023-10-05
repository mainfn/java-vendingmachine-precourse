package vendingmachine.view;

import vendingmachine.domain.coin.Changes;
import vendingmachine.domain.coin.Money;
import vendingmachine.domain.coin.ReturnedChanges;

public final class OutputView {

  public void printReturnedChanges(final ReturnedChanges changes) {
    System.out.println(changes);
  }

  public void printChanges(final Changes changes) {
    System.out.println(changes);
  }

  public void printInputMoney(final Money inputMoney) {
    System.out.println();
    System.out.printf("투입 금액: %d원\n", inputMoney.getAmount());
  }

}
