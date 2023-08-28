package vendingmachine;

import vendingmachine.controller.InputManager;
import vendingmachine.controller.MachineController;
import vendingmachine.domain.Machine;
import vendingmachine.util.RandomCoinWalletGenerator;
import vendingmachine.view.MachineView;

public class Application {

  public static void main(String[] args) {
    // TODO: 프로그램 구현
    final RandomCoinWalletGenerator randomCoinWalletGenerator = new RandomCoinWalletGenerator();
    final InputManager inputManager = new InputManager(randomCoinWalletGenerator);
    final MachineView machineView = new MachineView();
    final Machine machine = new Machine();
    final MachineController machineController = new MachineController(inputManager, machineView,
        machine);

    machineController.run();
  }
}
