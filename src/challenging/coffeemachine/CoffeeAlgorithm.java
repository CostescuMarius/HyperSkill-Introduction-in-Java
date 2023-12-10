package challenging.coffeemachine;

class CoffeeAlgorithm {
    final int WATER_AMOUNT_PER_CUP = 200;
    final int MILK_AMOUNT_PER_CUP = 50;
    final int COFFEE_BEANS_AMOUNT_PER_CUP = 15;
    private int waterAmount;
    private int milkAmount;
    private int coffeeBeansAmount;
    private int money;
    private int currentNoCups;
    private String currentState = "choosing an action";

    public CoffeeAlgorithm(int waterAmount, int milkAmount, int coffeeBeansAmount, int money, int currentNoCups) {
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.coffeeBeansAmount = coffeeBeansAmount;
        this.money = money;
        this.currentNoCups = currentNoCups;
    }

    public void coffeeMachineInput(String userInput) {
        switch (currentState) {
            case "choosing an action":
                if(userInput.equals("buy")) {
                    currentState = "choosing a type of coffee";
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                } else if(userInput.equals("remaining")) {
                    showMachineCoffeeState();
                    System.out.println("Write action (buy, fill, take, remaining, exit): ");
                } else if(userInput.equals("take")) {
                    take();
                    System.out.println("Write action (buy, fill, take, remaining, exit): ");
                } else if(userInput.equals("fill")) {
                    currentState = "filling coffee machine - add water";
                    System.out.println("\nWrite how many ml of water you want to add: ");
                }
                break;

            case "choosing a type of coffee":
                if(userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {
                    buy(userInput);

                    currentState = "choosing an action";
                    System.out.println("Write action (buy, fill, take, remaining, exit): ");
                } else if(userInput.equals("back")) {
                    currentState = "choosing an action";
                    System.out.println("Write action (buy, fill, take, remaining, exit): ");
                }
                break;

            case "filling coffee machine - add water":
                waterAmount += Integer.parseInt(userInput);

                currentState = "filling coffee machine - add milk";
                System.out.println("Write how many ml of milk you want to add: ");
                break;

            case "filling coffee machine - add milk":
                milkAmount += Integer.parseInt(userInput);

                currentState = "filling coffee machine - add coffee beans";
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;

            case "filling coffee machine - add coffee beans":
                coffeeBeansAmount += Integer.parseInt(userInput);

                currentState = "filling coffee machine - add cups";
                System.out.println("Write how many disposable cups you want to add:");
                break;

            case "filling coffee machine - add cups":
                currentNoCups += Integer.parseInt(userInput);

                currentState = "choosing an action";
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;

            default:
                break;
        }
    }

    private void showMachineCoffeeState() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", waterAmount);
        System.out.printf("%d ml of milk\n", milkAmount);
        System.out.printf("%d g of coffee beans\n", coffeeBeansAmount);
        System.out.printf("%d disposable cups\n", currentNoCups);
        System.out.printf("$%d of money\n\n", money);
    }

    private void buy(String coffeeOption) {
        switch (coffeeOption) {
            case "1":
                if (waterAmount < 250) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (coffeeBeansAmount < 16) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= 250;
                    coffeeBeansAmount -= 16;
                    currentNoCups--;
                    money += 4;
                }
                break;

            case "2":
                if (waterAmount < 350) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkAmount < 75) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (coffeeBeansAmount < 20) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= 350;
                    milkAmount -= 75;
                    coffeeBeansAmount -= 20;
                    currentNoCups--;
                    money += 7;
                }
                break;

            case "3":
                if (waterAmount < 200) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkAmount < 100) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (coffeeBeansAmount < 12) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= 200;
                    milkAmount -= 100;
                    coffeeBeansAmount -= 12;
                    currentNoCups--;
                    money += 6;
                }
                break;

            default:
                break;
        }
    }

    private void fill(int waterAdded, int milkAdded, int coffeeBeansAdded, int cupsAdded) {
        waterAmount += waterAdded;
        milkAmount += milkAdded;
        coffeeBeansAmount += coffeeBeansAdded;
        currentNoCups += cupsAdded;
    }

    private void take() {
        System.out.printf("\nI gave you $%d\n", money);
        money = 0;
    }

    private void estimateServingsNo(int noRequestedCups) {
        int maxCupsWithWater = waterAmount / WATER_AMOUNT_PER_CUP;
        int maxCupsWithMilk = milkAmount / MILK_AMOUNT_PER_CUP;
        int maxCupsWithCoffeeBeans = coffeeBeansAmount / COFFEE_BEANS_AMOUNT_PER_CUP;

        int maxCupsWithCoffee = Math.min(maxCupsWithWater, Math.min(maxCupsWithMilk, maxCupsWithCoffeeBeans));

        if (maxCupsWithCoffee == noRequestedCups) {
            System.out.println("Yes, I can make that amount of coffee ");
        } else if (maxCupsWithCoffee > noRequestedCups) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", maxCupsWithCoffee - noRequestedCups);
        } else if (maxCupsWithCoffee < noRequestedCups) {
            System.out.printf("No, I can make only %d cup(s) of coffee", maxCupsWithCoffee);
        }
    }

    private void ingredientCalculator(int noCups) {
        System.out.printf("For %d cups of coffee you will need:\n", noCups);
        System.out.printf("%d ml of water\n", noCups * WATER_AMOUNT_PER_CUP);
        System.out.printf("%d ml of milk\n", noCups * MILK_AMOUNT_PER_CUP);
        System.out.printf("%d g of coffee beans\n", noCups * COFFEE_BEANS_AMOUNT_PER_CUP);
    }

    private void makeCoffee() {
        String makingCoffee = """
                Starting to make a coffee
                Grinding coffee beans
                Boiling water
                Mixing boiled water with crushed coffee beans
                Pouring coffee into the cup
                Pouring some milk into the cup
                Coffee is ready!""";
        System.out.println(makingCoffee);
    }
}