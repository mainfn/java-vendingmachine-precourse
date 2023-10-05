package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class Changes extends Coins {

  private Changes(final List<Coin> coins) {
    super(coins);
  }

  public static Changes of(final List<Coin> coins) {
    return new Changes(coins);
  }


  @Override
  public String toString() {
    return super.format("자판기가 보유한 동전", false);
  }

  public boolean isEmpty() {
    return coinsMap.values()
        .stream()
        .noneMatch(count -> count > 0);
  }

  public ReturnedChanges returnChanges(final Money inputMoney) {
    final List<Coin> returningChanges = new ArrayList<>();

    while (isRemainedChanges()) {
      final Coin coin = takeMaxCoin(inputMoney);
      returningChanges.add(coin);
    }

    return ReturnedChanges.of(returningChanges);
  }

  private Coin takeMaxCoin(final Money money) {
    final List<Coin> coins = findAllCoinsEqualOrLessThan(money.getAmount());
    final Coin coin = coins.get(0);
    final Integer prevCount = coinsMap.get(coin);
    coinsMap.replace(coin, prevCount, prevCount - 1);

    return coin;
  }

  private boolean isRemainedChanges() {
    return coinsMap.entrySet()
        .stream()
        .anyMatch(c -> c.getValue() > 0);
  }

  private List<Coin> findAllCoinsEqualOrLessThan(final int amount) {
    return coinsMap.entrySet()
        .stream()
        .filter(c -> c.getValue() > 0)
        .filter(c -> c.getKey().getAmount() <= amount)
        .map(Entry::getKey)
        .collect(Collectors.toList());
  }
}
