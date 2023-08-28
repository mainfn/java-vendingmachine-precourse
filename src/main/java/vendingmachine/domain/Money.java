package vendingmachine.domain;

public final class Money {

  private int amount;

  private Money(final int amount) {
    this.amount = amount;
  }

  public static Money of(final int amount) {
    return new Money(amount);
  }

  public static Money empty() {
    return new Money(0);
  }

  public int getAmount() {
    return amount;
  }

  public void decrease(final Money money) {
    decrease(money.amount);
  }

  public void decrease(final int amount) {
    final int nextAmount = this.amount - amount;
    if (nextAmount < 0) {
      throw new IllegalArgumentException("Money.amount는 음수가 될 수 없습니다.");
    }
    this.amount = nextAmount;
  }

  public void increase(final int amount) {
    this.amount += amount;
  }

  public void increase(final Money money) {
    increase(money.amount);
  }

  public boolean isEqualOrLessThan(final int amount) {
    return this.amount <= amount;
  }

  public boolean isLessThan(final int amount) {
    return this.amount < amount;
  }

  public boolean isEqualOrMoreThan(final int amount) {
    return this.amount > amount;
  }
}
