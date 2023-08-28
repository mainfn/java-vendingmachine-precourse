package vendingmachine.domain;

public enum Coin {
  COIN_10(10),
  COIN_50(50),
  COIN_100(100),
  COIN_500(500);

  private final int amount;

  Coin(final int amount) {
    this.amount = amount;
  }

  // 추가 기능 구현

  public int getAmount() {
    return amount;
  }
}
