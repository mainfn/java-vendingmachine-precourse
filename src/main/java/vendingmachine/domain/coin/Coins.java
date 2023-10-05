package vendingmachine.domain.coin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Coins {

  protected final LinkedHashMap<Coin, Integer> coinsMap;

  protected Coins(final List<Coin> coins) {
    this.coinsMap = intoCoinsMap(coins);
  }


  private static LinkedHashMap<Coin, Integer> intoCoinsMap(final List<Coin> coins) {
    final LinkedHashMap<Coin, Integer> coinsMap = new LinkedHashMap<>();
    coinsMap.put(Coin.COIN_500, 0);
    coinsMap.put(Coin.COIN_100, 0);
    coinsMap.put(Coin.COIN_50, 0);
    coinsMap.put(Coin.COIN_10, 0);

    for (final Coin coin : coins) {
      final Integer coinCount = coinsMap.get(coin);
      coinsMap.replace(coin, coinCount, coinCount + 1);
    }
    return coinsMap;
  }

  protected String format(
      final String title,
      final boolean unprintZero
  ) {
    return String.format("%s\n%s",
        title,
        formatCoinsMap(unprintZero)
    );
  }

  private String formatCoinsMap(final boolean unprintZero) {
    final Stream<Entry<Coin, Integer>> coins = filterPrintableCoins(unprintZero);
    return coins.map(c ->
        String.format("%d원 - %d개", c.getKey().getAmount(), c.getValue())
    ).collect(Collectors.joining("\n"));
  }

  private Stream<Entry<Coin, Integer>> filterPrintableCoins(final boolean unprintZero) {
    return coinsMap.entrySet()
        .stream()
        .filter(e -> {
          if (unprintZero) {
            return e.getValue() > 0;
          }
          return true;
        });
  }
}
