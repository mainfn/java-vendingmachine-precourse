package vendingmachine.domain.coin;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public final class Changes {

  private final LinkedHashMap<Coin, Integer> changesMap;

  private Changes(final LinkedHashMap<Coin, Integer> changesMap) {
    this.changesMap = changesMap;
  }

  public static Changes of(final LinkedHashMap<Coin, Integer> changesMap) {
    return new Changes(changesMap);
  }

  @Override
  public String toString() {
    return changesMap.entrySet().stream()
        .filter(entry -> entry.getValue() > 0)
        .sorted((c1, c2) -> c2.getKey().getAmount() - c1.getKey().getAmount())
        .map(entry -> entry.getKey().getAmount() + "원 - " + entry.getValue() + "개")
        .collect(Collectors.joining("\n"));
  }
}
