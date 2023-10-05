package vendingmachine.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import vendingmachine.domain.coin.Coin;

public final class RandomNumberPicker {

  public int pick() {
    return Randoms.pickNumberInList(
        Arrays.asList(
            Coin.COIN_10.getAmount(),
            Coin.COIN_50.getAmount(),
            Coin.COIN_100.getAmount(),
            Coin.COIN_500.getAmount()
        )
    );
  }
}
