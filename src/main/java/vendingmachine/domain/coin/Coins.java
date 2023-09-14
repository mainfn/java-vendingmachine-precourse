package vendingmachine.domain.coin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import vendingmachine.domain.money.Money;

public final class Coins {

  // 잔돈을 반환할 때, 최소 갯수의 동전을 줘야 하기 때문에
  // LinkedHashMap에 동전을 저장하여 높은 금액 순으로 정렬할 수 있게 함
  private final LinkedHashMap<Coin, Integer> coinsMap;

  private Coins(final LinkedHashMap<Coin, Integer> coinsMap) {
    this.coinsMap = coinsMap;
  }

  public static Coins of(final List<Coin> coins) {
    final LinkedHashMap<Coin, Integer> coinsMap = createCoinsMap();
    // LinkedHashMap에 key - Coin 종류, value - Integer 갯수 형태로 저장
    for (final Coin coin : coins) {
      // 각 Coin 종류에 따라 갯수 증가
      final Integer oldCoinCount = coinsMap.get(coin);
      coinsMap.replace(coin, oldCoinCount + 1);
    }
    return new Coins(coinsMap);
  }

  public static LinkedHashMap<Coin, Integer> createCoinsMap() {
    final LinkedHashMap<Coin, Integer> coinsMap = new LinkedHashMap<>();
    coinsMap.put(Coin.COIN_500, 0);
    coinsMap.put(Coin.COIN_100, 0);
    coinsMap.put(Coin.COIN_50, 0);
    coinsMap.put(Coin.COIN_10, 0);
    return coinsMap;
  }

  public Changes exchangeAll(final Money money) {
    final LinkedHashMap<Coin, Integer> changesMap = createCoinsMap();

    while (true) {
      final Optional<Coin> coin = getMostCoinEqualOrMoreThan(money);
      if (!coin.isPresent()) {
        break;
      }
      final int prevCount = changesMap.get(coin.get());
      changesMap.replace(coin.get(), prevCount + 1);
      money.decrease(coin.get().getAmount());
    }

    return Changes.of(changesMap);
  }

  private Optional<Coin> getMostCoinEqualOrMoreThan(final Money money) {
    return coinsMap.entrySet()
        .stream()
        // 1개 이상 존재하는 코인만 필터링
        .filter(entry -> entry.getValue() > 0)
        // 보유 금액이 코인보다 많은지 -> 잔액이 490원이고 잔돈이 500원이면 변환 불가하기 때문
        .filter(entry -> money.isEqualOrMoreThan(entry.getKey().getAmount()))
        .map(Entry::getKey)
        .findFirst();
  }

  @Override
  public String toString() {
    return coinsMap.entrySet().stream()
        .sorted((c1, c2) -> c2.getKey().getAmount() - c1.getKey().getAmount())
        .map(entry -> entry.getKey().getAmount() + "원 - " + entry.getValue() + "개")
        .collect(Collectors.joining("\n"));
  }

}
