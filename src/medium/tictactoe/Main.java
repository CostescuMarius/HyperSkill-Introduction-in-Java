package medium.tictactoe;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String userInput = scanner.nextLine();

        TikTakToe newGame = new TikTakToe();
        char[][] table = new char[3][3];

        newGame.startGame(table);

        //newGame.getTableFromInput(userInput, table);

        newGame.printTable(table);

        boolean read = true;
        int movesNo = 1;
        while(read) {
            try{
                int coordX = scanner.nextInt();
                int coordY = scanner.nextInt();

                if(coordX > 3 || coordX < 1 || coordY > 3 || coordY < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if(table[coordX - 1][coordY - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if(movesNo % 2 == 1) {
                        table[coordX - 1][coordY - 1] = 'X';
                        newGame.printTable(table);
                    } else {
                        table[coordX - 1][coordY - 1] = 'O';
                        newGame.printTable(table);
                    }
                    movesNo++;
                    if(!newGame.getResult(table).equals("Game not finished")) {
                        read = false;
                    }
                }

            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
            }
        }

        System.out.print(newGame.getResult(table));
    }


}

