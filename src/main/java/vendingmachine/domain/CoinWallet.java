package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Optional;

public final class CoinWallet {

  private final LinkedHashMap<Coin, Integer> coinMap;

  private CoinWallet() {
    final LinkedHashMap<Coin, Integer> coinMap = new LinkedHashMap<>(4);
    // must be sorted desc
    coinMap.put(Coin.COIN_500, 0);
    coinMap.put(Coin.COIN_100, 0);
    coinMap.put(Coin.COIN_50, 0);
    coinMap.put(Coin.COIN_10, 0);
    this.coinMap = coinMap;
  }

  public static CoinWallet empty() {
    return new CoinWallet();
  }

  // 1. money가 0이 되거나 꺼낼 코인이 없을 때까지 CoinWallet에 담아서 반환
  public CoinWallet take(final Money money) {
    final CoinWallet coinWallet = new CoinWallet();

    while (true) {
      final Optional<Coin> coin = takeCoinEqualOrLessThan(money);
      if (!coin.isPresent()) {
        break;
      }
      coinWallet.add(coin.get());
    }
    return coinWallet;
  }

  public void add(final Coin coin) {
    final Integer oldCount = coinMap.get(coin);
    coinMap.replace(coin, oldCount, oldCount + 1);
  }

  private Optional<Coin> takeCoinEqualOrLessThan(final Money money) {

    for (final Entry<Coin, Integer> entry : coinMap.entrySet()) {
      final Coin coin = entry.getKey();
      final Integer count = entry.getValue();

      // 코인이 존재하면서 money 금액보다 같거나 작다면
      if (count > 0 && money.isEqualOrMoreThan(coin.getAmount())) {
        // Walle에 있던 코인 갯수 1 줄이고
        coinMap.replace(coin, count, count - 1);
        money.decrease(coin.getAmount());
        // 해당 코인 반환
        return Optional.of(coin);
      }

    }

    // 조건에 맞는 코인이 없는 경우
    return Optional.empty();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();

    for (final Entry<Coin, Integer> entry : coinMap.entrySet()) {
      final Integer amount = entry.getKey().getAmount();
      final Integer count = entry.getValue();

      sb.append(
          String.format("%d원 - %d개\n", amount, count)
      );
    }

    return sb.toString();
  }
}
