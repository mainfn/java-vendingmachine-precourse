package vendingmachine.view;

import vendingmachine.domain.coin.Changes;
import vendingmachine.domain.coin.Coins;

public final class OutputView {

  public void printCoinsInMachine(final Coins coins) {
    System.out.println(coins);
  }

  public void printChanges(final Changes changes) {
    System.out.println(changes);
  }
}
