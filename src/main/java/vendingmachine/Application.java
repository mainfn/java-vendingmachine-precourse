package vendingmachine;

import vendingmachine.controller.MainController;
import vendingmachine.domain.coin.CoinExchange;
import vendingmachine.util.RandomCoinPicker;
import vendingmachine.util.RandomNumberPicker;
import vendingmachine.view.InputValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class Application {

  public static void main(final String[] args) {
    final InputValidator inputValidator = new InputValidator();
    final InputView inputView = new InputView(inputValidator);
    final OutputView outputView = new OutputView();
    final RandomNumberPicker randomNumberPicker = new RandomNumberPicker();
    final RandomCoinPicker randomCoinPicker = new RandomCoinPicker(randomNumberPicker);
    final CoinExchange coinExchange = new CoinExchange(randomCoinPicker);
    final MainController mainController = new MainController(outputView, inputView, coinExchange);

    mainController.run();
  }
}
