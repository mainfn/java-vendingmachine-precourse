package vendingmachine.view;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

  @DisplayName("[상품명,가격,수량];[상품명,가격,수량] 형태의 입력은 검증 성공한다.")
  @Test
  void pat1() {
    // given
    final String input = "[콜라,1500,20];[사이다,1000,10]";
    final InputValidator inputValidator = new InputValidator();

    // when
    // then
    assertThatNoException()
        .isThrownBy(
            () -> inputValidator.validateItemsInput(input)
        );
  }

  @DisplayName("[상품명,가격,수량] 형태의 입력은 검증 성공한다.")
  @Test
  void pat2() {
    // given
    final String input = "[콜라,1500,20]";
    final InputValidator inputValidator = new InputValidator();

    // when
    // then
    assertThatNoException()
        .isThrownBy(
            () -> inputValidator.validateItemsInput(input)
        );
  }

  @DisplayName("상품명,가격,수량 형태의 입력은 검증 실패한다.")
  @Test
  void pat3() {
    // given
    final String input = "콜라,1500,20";
    final InputValidator inputValidator = new InputValidator();

    // when
    // then
    assertThatThrownBy(() -> inputValidator.validateItemsInput(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("상품 [콜라,1500,20];[사이다,1000,10]와 같은 양식으로 입력해야 합니다.");
  }

  @DisplayName("가격,수량이 숫자가 아닌 경우 검증 실패한다.")
  @Test
  void pat4() {
    // given
    final String input = "[콜라,1500a,20b]";
    final InputValidator inputValidator = new InputValidator();

    // when
    // then
    assertThatThrownBy(() -> inputValidator.validateItemsInput(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("상품 [콜라,1500,20];[사이다,1000,10]와 같은 양식으로 입력해야 합니다.");
  }

  @DisplayName("상품명이 문자가 아닌 경우 검증 실패한다.")
  @Test
  void pat5() {
    // given
    final String input = "[11,1500,20]";
    final InputValidator inputValidator = new InputValidator();

    // when
    // then
    assertThatThrownBy(() -> inputValidator.validateItemsInput(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("상품 [콜라,1500,20];[사이다,1000,10]와 같은 양식으로 입력해야 합니다.");
  }
}