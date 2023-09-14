package vendingmachine.domain.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.money.Money;

public final class RandomCoinGenerator {

  private int pickAmountEqualsOrLessThan(final Money money) {
    final List<Integer> amounts = Arrays.asList(10, 50, 100, 500)
        .stream()
        .filter(amount -> amount <= money.getAmount())
        .collect(Collectors.toList());

    return Randoms.pickNumberInList(amounts);
  }

  public Coins generate(final Money money) {
    final List<Coin> coins = new ArrayList<>();

    while (money.isEqualOrMoreThan(10)) {
      final int amount = pickAmountEqualsOrLessThan(money);
      money.decrease(amount);
      coins.add(Coin.of(amount));
    }

    return Coins.of(coins);
  }
}
