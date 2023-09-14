package vendingmachine.domain.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.money.Money;

public final class RandomCoinGenerator {

  private Coin generateCoin(final Money money) {
    final int amount = Randoms.pickNumberInList(Arrays.asList(10, 50, 100, 500));
    if (money.isEqualOrMoreThan(amount)) {
      money.decrease(amount);
      return Coin.of(amount);
    }
    return generateCoin(money);
  }

  public Coins generate(final Money money) {
    final List<Coin> coins = new ArrayList<>();

    while (money.isEqualOrMoreThan(0)) {
      final Coin coin = generateCoin(money);
      coins.add(coin);
    }

    return Coins.of(coins);
  }
}
