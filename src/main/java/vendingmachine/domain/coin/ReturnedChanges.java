package vendingmachine.domain.coin;

import java.util.List;

public final class ReturnedChanges extends Coins {

  private ReturnedChanges(final List<Coin> coins) {
    super(coins);
  }

  public static ReturnedChanges of(final List<Coin> coins) {
    return new ReturnedChanges(coins);
  }

  @Override
  public String toString() {
    return super.format("잔돈", true);
  }
}
