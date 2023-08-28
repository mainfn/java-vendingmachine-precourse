package vendingmachine.util;

import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinWallet;

public final class RandomCoinWalletGenerator {

  private final List<Coin> coinPicker = Arrays.asList(
      Coin.COIN_10,
      Coin.COIN_50,
      Coin.COIN_100,
      Coin.COIN_500
  );
  private final RandomNumberGenerator randomNumberGenerator =
      new RandomNumberGenerator(
          Coin.COIN_10.ordinal(),
          Coin.COIN_500.ordinal()
      );

  public CoinWallet generateCoinWallet(int amount) {
    final CoinWallet coinWallet = CoinWallet.empty();

    while (amount >= 10) {
      final Coin coin = generateCoin(amount);
      coinWallet.add(coin);
      amount -= coin.getAmount();
    }

    return coinWallet;
  }

  private Coin generateCoin(final int amount) {
    final int at = randomNumberGenerator.generate();
    final Coin pickedCoin = coinPicker.get(at);

    if (amount < pickedCoin.getAmount()) {
      return generateCoin(amount);
    }
    return pickedCoin;
  }
}
