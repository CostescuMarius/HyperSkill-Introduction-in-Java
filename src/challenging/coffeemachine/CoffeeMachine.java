package challenging.coffeemachine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeAlgorithm coffeeAlgorithm = new CoffeeAlgorithm(400, 540, 120, 550, 9);

        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        String userInput = "";
        do {
            userInput = scanner.next();
            coffeeAlgorithm.coffeeMachineInput(userInput);
        } while(!userInput.equals("exit"));


        /*System.out.println("Write how many cups of coffee you will need: ");
        int noRequestedCups = scanner.nextInt();

        coffeeAlgorithm.estimateServingsNo(noRequestedCups);

        coffeeAlgorithm.ingredientCalculator(noCups);*/
    }
}
