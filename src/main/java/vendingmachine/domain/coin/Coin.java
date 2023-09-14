package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import vendingmachine.domain.money.Money;

public enum Coin {
  COIN_500(500),
  COIN_100(100),
  COIN_50(50),
  COIN_10(10);

  private final static List<Coin> variants = new ArrayList<>();

  static {
    Collections.addAll(variants, values());
  }

  private final int amount;

  Coin(final int amount) {
    this.amount = amount;
  }

  public static Coin of(final int amount) {
    return findByAmount(amount)
        .orElseThrow(() -> new IllegalArgumentException("잘못된 코인 단위를 입력 하셨습니다."));
  }

  private static Optional<Coin> findByAmount(final int amount) {
    return variants.stream()
        .filter(v -> v.amount == amount)
        .findFirst();
  }

  public static Optional<Coin> exchange(final Money money) {
    if (money.isEqualOrMoreThan(COIN_500.amount)) {
      money.decrease(COIN_500.amount);
      return Optional.of(COIN_500);
    }
    if (money.isEqualOrMoreThan(COIN_100.amount)) {
      money.decrease(COIN_100.amount);
      return Optional.of(COIN_100);
    }
    if (money.isEqualOrMoreThan(COIN_50.amount)) {
      money.decrease(COIN_50.amount);
      return Optional.of(COIN_50);
    }
    if (money.isEqualOrMoreThan(COIN_10.amount)) {
      money.decrease(COIN_10.amount);
      return Optional.of(COIN_10);
    }
    return Optional.empty();
  }

  public int getAmount() {
    return amount;
  }
}
