package vendingmachine.util;

import camp.nextstep.edu.missionutils.Randoms;

public final class RandomNumberGenerator {

  private final int startInclusive;
  private final int endInclusive;

  public RandomNumberGenerator(
      final int startInclusive,
      final int endInclusive
  ) {
    this.startInclusive = startInclusive;
    this.endInclusive = endInclusive;
  }

  public int generate() {
    return Randoms.pickNumberInRange(startInclusive, endInclusive);
  }
}
