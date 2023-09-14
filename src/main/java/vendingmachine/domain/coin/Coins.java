package vendingmachine.domain.coin;

import java.util.LinkedHashMap;
import java.util.List;

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


}
