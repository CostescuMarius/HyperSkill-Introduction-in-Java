package hard.lastpencil;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class LastPencil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();

        System.out.println("How many pencils would you like to use:");

        int pencilsNo = 0;
        boolean incorrectPencilsNo = true;

        while(incorrectPencilsNo) {
            String userInput = scanner.nextLine().trim();
            if(userInput.isEmpty()) {
                System.out.println("The number of pencils should be numeric");
            } else {
                try {
                    pencilsNo = Integer.parseInt(userInput);

                    if(pencilsNo < 0) {
                        System.out.println("The number of pencils should be numeric");
                    } else if(pencilsNo == 0) {
                        System.out.println("The number of pencils should be positive");
                    } else {
                        incorrectPencilsNo = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The number of pencils should be numeric");
                }
            }
        }

        String[] players = new String[] {"John", "Jack"};

        System.out.printf("Who will be the first (%s, %s):\n", players[0], players[1]);
        String firstPlayer = null;
        boolean incompatibleName = true;
        while(incompatibleName) {
            firstPlayer = scanner.next();
            if(firstPlayer.equals(players[0]) || firstPlayer.equals(players[1])) {
                incompatibleName = false;
            } else {
                System.out.printf("Choose between '%s' and '%s'\n", players[0], players[1]);
            }
        }

        int turnPlayerId = players[0].equals(firstPlayer)? 0 : 1;


        while(pencilsNo > 0) {
            for(int i = 0; i < pencilsNo; i++) {
                System.out.print("|");
            }
            System.out.println();
            System.out.println(players[turnPlayerId] + " turn");

            if(turnPlayerId == 0) {
                boolean incorrectMove = true;
                int removedPencilsNo = 0;
                while (incorrectMove) {
                    String userInput = scanner.next().trim();
                    try {
                        removedPencilsNo = Integer.parseInt(userInput);

                        if (removedPencilsNo > 0 && removedPencilsNo <= 3 && removedPencilsNo <= pencilsNo) {
                            incorrectMove = false;
                            pencilsNo -= removedPencilsNo;
                        } else if (removedPencilsNo <= 0 || removedPencilsNo > 3) {
                            System.out.println("Possible values: '1', '2' or '3'");
                        } else {
                            System.out.println("Too many pencils were taken");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }
            } else {
                if(pencilsNo == 1) {
                    System.out.println(1);
                    pencilsNo--;
                }
                else if(pencilsNo % 4 == 0) {
                    System.out.println(3);
                    pencilsNo -= 3;
                } else if(pencilsNo % 4 == 1) {
                    int removedPencilsNo = random.nextInt(3) + 1;
                    System.out.println(removedPencilsNo);
                    pencilsNo -= removedPencilsNo;
                } else if(pencilsNo % 4 == 2) {
                    System.out.println(1);
                    pencilsNo -= 1;
                } else if(pencilsNo % 4 == 3) {
                    System.out.println(2);
                    pencilsNo -= 2;
                }
            }

            turnPlayerId = 1 - turnPlayerId;
        }
        System.out.printf("%s won!", players[turnPlayerId]);
    }
}
