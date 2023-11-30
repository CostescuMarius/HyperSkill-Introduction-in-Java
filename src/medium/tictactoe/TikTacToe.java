package medium.tictactoe;

class TikTakToe {
    public void getTableFromInput(String input, char[][] table) {
        int table_index = 0;
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                table[i][j] = input.charAt(table_index++);
            }
        }
    }

    public void printTable(char[][] table) {
        System.out.println("---------");

        for(int i = 0; i < table.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public String getResult(char[][] table) {
        boolean winnerX = isWinnerX(table);
        boolean winnerO = isWinnerO(table);
        boolean anyEmptyCells = areEmptyCells(table);
        boolean gamePossible = isGamePossible(table);

        if(winnerX && winnerO || !gamePossible) {
            return "Impossible";
        } else if(!winnerX && !winnerO && anyEmptyCells) {
            return "Game not finished";
        } else if(!winnerX && !winnerO && !anyEmptyCells) {
            return "Draw";
        } else if(winnerX && !winnerO) {
            return "X wins";
        } else if(!winnerX && winnerO) {
            return "O wins";
        } else {
            return "Unknown";
        }
    }

    private boolean isGamePossible(char[][] table) {
        boolean gamePossible = true;
        int noX = 0;
        int noO = 0;
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                if(table[i][j] == 'X') {
                    noX++;
                } else if(table[i][j] == 'O') {
                    noO++;
                }
            }
        }

        if(noX > noO + 1) {
            gamePossible = false;
        } else if(noO > noX + 1) {
            gamePossible = false;
        }

        return gamePossible;
    }

    private boolean areEmptyCells(char[][] table) {
        boolean anyEmptyCells = false;
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                if(table[i][j] == '_') {
                    anyEmptyCells = true;
                }
            }
        }
        return anyEmptyCells;
    }

    private boolean isWinnerX(char[][] table) {
        boolean winnerX = false;

        //check rows
        for(int i = 0; i < table.length; i++) {
            if(table[i][0] == 'X' && table[i][1] == 'X' && table[i][2] == 'X') {
                winnerX = true;
            }
        }

        //check columns
        for(int j = 0; j < table[0].length; j++) {
            if(table[0][j] == 'X' && table[1][j] == 'X' && table[2][j] == 'X') {
                winnerX = true;
            }
        }

        //check diagonals
        if(table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X') {
            winnerX = true;
        }

        if(table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X') {
            winnerX = true;
        }

        return winnerX;
    }

    private boolean isWinnerO(char[][] table) {
        boolean winnerO = false;

        //check rows
        for(int i = 0; i < table.length; i++) {
            if(table[i][0] == 'O' && table[i][1] == 'O' && table[i][2] == 'O') {
                winnerO = true;
            }
        }

        //check columns
        for(int j = 0; j < table[0].length; j++) {
            if(table[0][j] == 'O' && table[1][j] == 'O' && table[2][j] == 'O') {
                winnerO = true;
            }
        }

        //check diagonals
        if(table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O') {
            winnerO = true;
        }

        if(table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O') {
            winnerO = true;
        }

        return winnerO;
    }

    public void startGame(char[][] table) {
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                table[i][j] = '_';
            }
        }
    }
}