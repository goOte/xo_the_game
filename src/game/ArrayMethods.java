package game;

public class ArrayMethods {

    private final static int ROW_LENGTH = 3;
    private final static int LINE_LENGTH = 3;
    private final static int NO_MORE_EMPTY_CELLS = 5;
    private final static char DEFAULT_VALUE = ' ';

    public char[][] gameTable = new char[ROW_LENGTH][LINE_LENGTH];

    private int[] line1Cells = {0, 0, 0, 1, 0, 2};
    private int[] line2Cells = {1, 0, 1, 1, 1, 2};
    private int[] line3Cells = {2, 0, 2, 1, 2, 2};
    private int[] row1Cells = {0, 0, 1, 0, 2, 0};
    private int[] row2Cells = {0, 1, 1, 1, 2, 1};
    private int[] row3Cells = {0, 2, 1, 2, 2, 2};
    private int[] diag1Cells = {0, 0, 1, 1, 2, 2};
    private int[] diag2Cells = {2, 0, 1, 1, 0, 2};

    private int[][] gameTableCells = {line1Cells, line2Cells, line3Cells,
            row1Cells, row2Cells, row3Cells,
            diag1Cells, diag2Cells};

    public void clearGameTable() {
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                gameTable[i][j] = DEFAULT_VALUE;
            }
        }
    }

    public void showGameTable() {
        System.out.println("    1   2   3");
        System.out.println("  -------------");
        for (int i=0; i < ROW_LENGTH; i++) {
            System.out.println((i+1) + " | " + gameTable[i][0] + " | " + gameTable[i][1] + " | " + gameTable[i][2] + " |");
            System.out.println("  -------------");
        }
        System.out.println();
    }

    public char[][] getGameTable() {
        char[][] getTable = new char[ROW_LENGTH][LINE_LENGTH];
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                getTable[i][j] = gameTable[i][j];
            }
        }
        return getTable;
    }

    public void setGameTable(char[][] newGameTable) {
        gameTable = newGameTable;
    }

    public boolean checkUserInput(int row, int line) {
        if (gameTable[row][line] != DEFAULT_VALUE) {
            System.out.println("Ячейка уже занята, пожалуйста выберете другую ячейку!");
            return false;
        } else {
            return true;
        }
    }

    public void acceptUserInput(char playerChar, int row, int line) {
        gameTable[row][line] = playerChar;
    }

    public boolean checkGameTable(char playerChar) {

        return checkDiagonals(playerChar) || checkLines(playerChar) || checkRows(playerChar);
    }

    private boolean checkRows(char playerChar) {
        boolean result = false;
        for (int i=0; i < ROW_LENGTH; i++) {
            if (gameTable[i][0] == playerChar
                    && gameTable[i][0] == gameTable[i][1]
                    && gameTable[i][0] == gameTable[i][2]) {
                result = true;
                break;
            }
            else {
                result = false;
            }

        }
        return result;
    }

    private boolean checkLines(char playerChar) {
        boolean result = false;
        for (int j=0; j < LINE_LENGTH; j++) {
            if (gameTable[0][j] == playerChar
                    && gameTable[0][j] == gameTable[1][j]
                    && gameTable[0][j] == gameTable[2][j]) {
                result = true;
                break;
            }
            else result = false;
        }
        return result;
    }

    private boolean checkDiagonals(char playerChar) {
        if (gameTable[1][1] == playerChar
                && gameTable[1][1] == gameTable[0][0]
                && gameTable[1][1] == gameTable[2][2]) {
            return true;
        }
        else if (gameTable[1][1] == playerChar
                    && gameTable[1][1] == gameTable[0][2]
                    && gameTable[1][1] == gameTable[2][0]) {
            return true;
        }
        else {
            return false;
        }
    }

    // ИИ
    public void useRoboBrain(char playerChar) {

        boolean winIndex = false;

        // Сразу пробует походить в центр
        if (!superRoboMove(playerChar)) {
            // Если центр занят, начинает просчитывать вес каждой строки\ряда\диагонали
            int line0Index = readLineIndex(0, playerChar);
            int line1Index = readLineIndex(1, playerChar);
            int line2Index = readLineIndex(2, playerChar);
            int row0Index = readRowIndex(0, playerChar);
            int row1Index = readRowIndex(1, playerChar);
            int row2Index = readRowIndex(2, playerChar);
            int diag1Index = readFirstDiagonalIndex(playerChar);
            int diag2Index = readSecondDiagonalIndex(playerChar);

/*
            System.out.println(line0Index + " " + line1Index + " " + line2Index);
            System.out.println(row0Index + " " + row1Index + " " + row2Index);
            System.out.println(diag1Index + " " + diag2Index);
*/

            int[] gameTableIndex = {line0Index, line1Index, line2Index,
                    row0Index, row1Index, row2Index,
                    diag1Index, diag2Index};

            // Если находит выиграшный ход - ходит туда
            for (int k = 0; k < gameTableIndex.length; k++) {
                if (gameTableIndex[k] == 2) {
                    roboMove(gameTableCells[k], playerChar);
                    winIndex = true;
                    break;
                }
            }

            // Если нет, ищет самое уязвимое место (наименьший вес), и ходит туда
            if (!winIndex) {
                int minIndex = 0;
                for (int i = 1; i < gameTableIndex.length; i++) {
                    if (gameTableIndex[i] < gameTableIndex[minIndex]) {
                        minIndex = i;
                    }
                }

                roboMove(gameTableCells[minIndex], playerChar);
            }
        }
    }

    private void roboMove(int[] cells, char playerChar) {

        for (int i = 0, j = 1; i <= 4; i = i + 2, j = j + 2) {
            if (gameTable[cells[i]][cells[j]] == DEFAULT_VALUE) {
                gameTable[cells[i]][cells[j]] = playerChar;
                break;
            }
        }

    }

    private boolean superRoboMove(char playerChar) {

        if (gameTable[1][1] == DEFAULT_VALUE) {
            gameTable[1][1] = playerChar;
            return true;
        } else {
            return false;
        }

    }

    private int readLineIndex(int lineNumber, char playerChar) {

        int lineIndex = 0;
        int emptyCell = 0;

        char enemyChar = switchPlayer(playerChar);

        for (int i = 0; i < ROW_LENGTH; i++) {
            if (gameTable[lineNumber][i] == playerChar) {
                lineIndex++;
            } else if (gameTable[lineNumber][i] == enemyChar) {
                lineIndex--;
            } else {
                emptyCell++;
            }
        }

        if (emptyCell > 0) {
            return lineIndex;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readRowIndex(int rowNumber, char playerChar) {

        int rowIndex = 0;
        int emptyCell = 0;

        char enemy_char = switchPlayer(playerChar);

        for (int i = 0; i < LINE_LENGTH; i++) {
            if (gameTable[i][rowNumber] == playerChar) {
                rowIndex++;
            } else if (gameTable[i][rowNumber] == enemy_char) {
                rowIndex--;
            } else {
                emptyCell++;
            }
        }

        if (emptyCell > 0) {
            return rowIndex;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readFirstDiagonalIndex(char playerChar) {

        int diagonalIndex = 0;
        int emptyCell = 0;

        char enemyChar = switchPlayer(playerChar);

        for (int i = 0, j = 0; i < ROW_LENGTH; i++, j++) {
            if (gameTable[i][j] == playerChar) {
                diagonalIndex++;
            } else if (gameTable[i][j] == enemyChar) {
                diagonalIndex--;
            } else {
                emptyCell++;
            }
        }

        if (emptyCell > 0) {
            return diagonalIndex;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readSecondDiagonalIndex(char playerChar) {

        int diagonalIndex = 0;
        int emptyCell = 0;

        char enemyChar = switchPlayer(playerChar);

        for (int i = ROW_LENGTH - 1, j = 0; i >= 0; i--, j++) {
            if (gameTable[i][j] == playerChar) {
                diagonalIndex++;
            } else if (gameTable[i][j] == enemyChar) {
                diagonalIndex--;
            } else {
                emptyCell++;
            }
        }

        if (emptyCell > 0) {
            return diagonalIndex;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    public char switchPlayer(char prevPlayer) {
        char nextPlayer;
        if (prevPlayer == 'X') {
            nextPlayer = 'O';
        } else {
            nextPlayer = 'X';
        }
        return nextPlayer;
    }

}
