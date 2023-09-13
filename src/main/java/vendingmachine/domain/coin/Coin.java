package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    if (!variants.contains(amount)) {
      throw new IllegalArgumentException("잘못된 코인 단위를 입력 하셨습니다.");
    }
    return variants.get(amount);
  }


  public int getAmount() {
    return amount;
  }
}
