package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.domain.coin.RandomCoinGenerator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class Application {

  public static void main(final String[] args) {
    final RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator();
    final InputView inputView = new InputView(randomCoinGenerator);
    final OutputView outputView = new OutputView();
    final MachineController machineController = new MachineController(inputView, outputView);

    machineController.start();
  }
}
