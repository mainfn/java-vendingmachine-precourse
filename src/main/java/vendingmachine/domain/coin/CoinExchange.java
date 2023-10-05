package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.util.RandomCoinPicker;

public final class CoinExchange {

  private final RandomCoinPicker randomCoinPicker;

  public CoinExchange(final RandomCoinPicker randomCoinPicker) {
    this.randomCoinPicker = randomCoinPicker;
  }

  public Changes exchangeUntilOutOf(final Money money) {
    List<Coin> coins = new ArrayList<>();

    while (money.hasEqualOrMoreAmountThan(Coin.COIN_10.getAmount())) {
      final Coin coin = exchange(money);
      coins.add(coin);
    }

    return Changes.of(coins);
  }

  private Coin exchange(final Money money) {
    final Coin coin = randomCoinPicker.pick();

    if (money.hasEqualOrMoreAmountThan(coin.getAmount())) {
      money.decrease(coin.getAmount());
      return coin;
    }
    return exchange(money);
  }


}
