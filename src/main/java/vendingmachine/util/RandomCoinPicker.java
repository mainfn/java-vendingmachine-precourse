package vendingmachine.util;

import vendingmachine.domain.coin.Coin;

public final class RandomCoinPicker {

  private final RandomNumberPicker randomNumberPicker;

  public RandomCoinPicker(final RandomNumberPicker randomNumberPicker) {
    this.randomNumberPicker = randomNumberPicker;
  }

  public Coin pick() {
    return Coin.of(randomNumberPicker.pick());
  }
}
