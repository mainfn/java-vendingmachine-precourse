package vendingmachine.domain.coin;

import java.util.LinkedHashMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vendingmachine.domain.money.Money;

public final class Coins {

  // 잔돈을 반환할 때, 최소 갯수의 동전을 줘야 하기 때문에
  // LinkedHashMap에 동전을 저장하여 높은 금액 순으로 정렬할 수 있게 함
  private final LinkedHashMap<Coin, Integer> coins;

  private Coins(final LinkedHashMap<Coin, Integer> coins) {
    this.coins = coins;
  }

  public static Coins of(final Money money) {
    // 입력한 금액만큼 Coin으로 변환 후 LinkedHashMap에
    // key - Coin 종류, value - Integer 갯수 형태로 저장
    throw new NotImplementedException();
  }


}
