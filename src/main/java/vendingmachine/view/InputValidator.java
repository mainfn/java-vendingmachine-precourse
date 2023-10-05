package vendingmachine.view;

import java.util.regex.Pattern;

public final class InputValidator {

  private static final String ITEM_REGEX =
      "^(\\[\\p{L}+,\\d+,\\d+\\];)+\\[\\p{L}+,\\d+,\\d+\\]$";
  private static final String ITEMS_REGEX =
      "^\\[\\p{L}+,\\d+,\\d+\\]$";

  public void validateItemsInput(final String input) {
    if (Pattern.matches(ITEM_REGEX, input) || Pattern.matches(ITEMS_REGEX, input)) {
      return;
    }
    throw new IllegalArgumentException("상품 [콜라,1500,20];[사이다,1000,10]와 같은 양식으로 입력해야 합니다.");
  }
}
