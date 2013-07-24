public class ArrayMethods {

    public final static int ROW_LENGTH = 3;
    public final static int LINE_LENGTH = 3;
    public final static char DEFAULT_VALUE = ' ';

    public char[][] game_table = new char[ROW_LENGTH][LINE_LENGTH];

    public void clearGameTable() {
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                game_table[i][j] = DEFAULT_VALUE;
            }
        }
    }

    public void showGameTable() {
        System.out.println("    1   2   3");
        System.out.println("  -------------");
        for (int i=0; i < ROW_LENGTH; i++) {
            System.out.println((i+1) + " | " + game_table[i][0] + " | " + game_table[i][1] + " | " + game_table[i][2] + " |");
            System.out.println("  -------------");
        }
        System.out.println();
    }

    public boolean checkUserInput(int row, int line) {
        if (game_table[row][line] != DEFAULT_VALUE) {
            System.out.println("Ячейка уже занята, пожалуйста выберете другую ячейку!");
            return false;
        } else {
            return true;
        }
    }

    public void acceptUserInput(char playerChar, int row, int line) {
        game_table[row][line] = playerChar;
    }

    public boolean checkGameTable(char playerChar) {
        if (checkDiagonals(playerChar) || checkLines(playerChar) || checkRows(playerChar)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkRows(char playerChar) {
        boolean result = false;
        for (int i=0; i < ROW_LENGTH; i++) {
            if (game_table[i][0] == playerChar
                    && game_table[i][0] == game_table[i][1]
                    && game_table[i][0] == game_table[i][2]) {
                result = true;
                break;
            }
            else {
                result = false;
            }

        }
        return result;
    }

    public boolean checkLines(char playerChar) {
        boolean result = false;
        for (int j=0; j < LINE_LENGTH; j++) {
            if (game_table[0][j] == playerChar
                    && game_table[0][j] == game_table[1][j]
                    && game_table[0][j] == game_table[2][j]) {
                result = true;
                break;
            }
            else result = false;
        }
        return result;
    }

    public boolean checkDiagonals(char playerChar) {
        if (game_table[1][1] == playerChar
                && game_table[1][1] == game_table[0][0]
                && game_table[1][1] == game_table[2][2]) {
            return true;
        }
        else if (game_table[1][1] == playerChar
                    && game_table[1][1] == game_table[0][2]
                    && game_table[1][1] == game_table[2][0]) {
            return true;
        }
        else {
            return false;
        }
    }
}
