package vendingmachine.domain.coin;

public final class Money {

  private int amount;

  public Money(final int amount) {
    validateAmount(amount);
    this.amount = amount;
  }

  public static Money of(final int amount) {
    return new Money(amount);
  }

  private static void validateAmount(final int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Money의 잔액은 0 미만으로 설정될 수 없습니다.");
    }
  }

  public static Money zero() {
    return Money.of(0);
  }

  public boolean hasEqualOrMoreAmountThan(final int amount) {
    return this.amount >= amount;
  }

  public void decrease(final int amount) {
    final int nextAmount = this.amount - amount;
    validateAmount(nextAmount);

    this.amount = nextAmount;
  }

  public int getAmount() {
    return amount;
  }
}
