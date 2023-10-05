package vendingmachine.domain.coin;

import java.util.Arrays;
import java.util.List;

public enum Coin {
  COIN_500(500),
  COIN_100(100),
  COIN_50(50),
  COIN_10(10);

  private static final List<Coin> coins = Arrays.asList(
      COIN_500,
      COIN_100,
      COIN_50,
      COIN_10
  );
  private final int amount;

  Coin(final int amount) {
    this.amount = amount;
  }

  // 추가 기능 구현
  public static List<Coin> getCoins() {
    return coins;
  }

  public static Coin of(final int amount) {
    return coins.stream()
        .filter(c -> c.amount == amount)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("잘못된 Coin 단위를 입력하였습니다."));
  }

  public static Coin getMaxCoinLessThan(final Money money) {
    for (final Coin coin : coins) {
      if (money.hasEqualOrMoreAmountThan(coin.getAmount())) {
        return coin;
      }
    }
    throw new IllegalArgumentException("유효하지 않은 단위입니다.");
  }

  public int getAmount() {
    return amount;
  }
}
