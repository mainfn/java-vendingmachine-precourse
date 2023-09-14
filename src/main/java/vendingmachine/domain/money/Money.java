package vendingmachine.domain.money;

public final class Money {

  private int amount;

  private Money(final int amount) {
    this.amount = amount;
  }

  public static Money of(final int amount) {
    validateAmount(amount);
    return new Money(amount);
  }

  private static void validateAmount(final int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Money 잔액은 최소 0입니다.");
    }
  }

  public void increase(final Money other) {
    amount += other.amount;
  }

  public void increase(final int amount) {
    this.amount += amount;
  }

  public void decrease(final Money other) {
    decrease(other.amount);
  }

  public void decrease(final int amount) {
    final int nextAmount = this.amount - amount;
    validateAmount(nextAmount);
    this.amount = nextAmount;
  }

  public boolean isEqualOrMoreThan(final Money other) {
    return isEqualOrMoreThan(other.amount);
  }

  public boolean isEqualOrMoreThan(final int amount) {
    return this.amount >= amount;
  }

  public boolean isDivisibleBy(final Money other) {
    return amount % other.amount == 0;
  }

  public int getAmount() {
    return amount;
  }
}
