package game;

public class ArrayMethods {

    public final static int ROW_LENGTH = 3;
    public final static int LINE_LENGTH = 3;
    private final static int NO_MORE_EMPTY_CELLS = 5;
    public final static char DEFAULT_VALUE = ' ';

    public char[][] game_table = new char[ROW_LENGTH][LINE_LENGTH];

    private int[] line_1_cells = {0, 0, 0, 1, 0, 2};
    private int[] line_2_cells = {1, 0, 1, 1, 1, 2};
    private int[] line_3_cells = {2, 0, 2, 1, 2, 2};
    private int[] row_1_cells = {0, 0, 1, 0, 2, 0};
    private int[] row_2_cells = {0, 1, 1, 1, 2, 1};
    private int[] row_3_cells = {0, 2, 1, 2, 2, 2};
    private int[] diag_1_cells = {0, 0, 1, 1, 2, 2};
    private int[] diag_2_cells = {2, 0, 1, 1, 0, 2};

    private int[][] game_table_cells = {line_1_cells, line_2_cells, line_3_cells,
                                        row_1_cells, row_2_cells, row_3_cells,
                                        diag_1_cells, diag_2_cells};

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

    public char[][] getGameTable() {
        char[][] get_table = new char[ROW_LENGTH][LINE_LENGTH];
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                get_table[i][j] = game_table[i][j];
            }
        }
        return get_table;
    }

    public void setGame_table(char[][] new_game_table) {
        game_table = new_game_table;
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

        return checkDiagonals(playerChar) || checkLines(playerChar) || checkRows(playerChar);
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

    // ИИ
    public void useRoboBrain(char player_char) {

        boolean win_index = false;

        // Сразу пробует походить в центр
        if (!superRoboMove(player_char)) {
            // Если центр занят, начинает просчитывать вес каждой строки\ряда\диагонали
            int line_0_index = readLineIndex(0, player_char);
            int line_1_index = readLineIndex(1, player_char);
            int line_2_index = readLineIndex(2, player_char);
            int row_0_index = readRowIndex(0, player_char);
            int row_1_index = readRowIndex(1, player_char);
            int row_2_index = readRowIndex(2, player_char);
            int diag_1_index = readFirstDiagonalIndex(player_char);
            int diag_2_index = readSecondDiagonalIndex(player_char);

/*
            System.out.println(line_0_index + " " + line_1_index + " " + line_2_index);
            System.out.println(row_0_index + " " + row_1_index + " " + row_2_index);
            System.out.println(diag_1_index + " " + diag_2_index);
*/

            int[] game_table_index = {line_0_index, line_1_index, line_2_index,
                    row_0_index, row_1_index, row_2_index,
                    diag_1_index, diag_2_index};

            // Если находит выиграшный ход - ходит туда
            for (int k = 0; k < game_table_index.length; k++) {
                if (game_table_index[k] == 2) {
                    roboMove(game_table_cells[k], player_char);
                    win_index = true;
                    break;
                }
            }

            // Если нет, ищет самое уязвимое место (наименьший вес), и ходит туда
            if (!win_index) {
                int min_index = 0;
                for (int i = 1; i < game_table_index.length; i++) {
                    if (game_table_index[i] < game_table_index[min_index]) {
                        min_index = i;
                    }
                }

                roboMove(game_table_cells[min_index], player_char);
            }
        }
    }

    private void roboMove(int[] cells, char player_char) {

        for (int i = 0, j = 1; i <= 4; i = i + 2, j = j + 2) {
            if (game_table[cells[i]][cells[j]] == DEFAULT_VALUE) {
                game_table[cells[i]][cells[j]] = player_char;
                break;
            }
        }

    }

    private boolean superRoboMove(char player_char) {

        if (game_table[1][1] == DEFAULT_VALUE) {
            game_table[1][1] = player_char;
            return true;
        } else {
            return false;
        }

    }

    private int readLineIndex(int line_number, char player_char) {

        int line_index = 0;
        int empty_cell = 0;

        char enemy_char = switchPlayer(player_char);

        for (int i = 0; i < ROW_LENGTH; i++) {
            if (game_table[line_number][i] == player_char) {
                line_index++;
            } else if (game_table[line_number][i] == enemy_char) {
                line_index--;
            } else {
                empty_cell++;
            }
        }

        if (empty_cell > 0) {
            return line_index;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readRowIndex(int row_number, char player_char) {

        int row_index = 0;
        int empty_cell = 0;

        char enemy_char = switchPlayer(player_char);

        for (int i = 0; i < LINE_LENGTH; i++) {
            if (game_table[i][row_number] == player_char) {
                row_index++;
            } else if (game_table[i][row_number] == enemy_char) {
                row_index--;
            } else {
                empty_cell++;
            }
        }

        if (empty_cell > 0) {
            return row_index;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readFirstDiagonalIndex(char player_char) {

        int diagonal_index = 0;
        int empty_cell = 0;

        char enemy_char = switchPlayer(player_char);

        for (int i = 0, j = 0; i < ROW_LENGTH; i++, j++) {
            if (game_table[i][j] == player_char) {
                diagonal_index++;
            } else if (game_table[i][j] == enemy_char) {
                diagonal_index--;
            } else {
                empty_cell++;
            }
        }

        if (empty_cell > 0) {
            return diagonal_index;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    private int readSecondDiagonalIndex(char player_char) {

        int diagonal_index = 0;
        int empty_cell = 0;

        char enemy_char = switchPlayer(player_char);

        for (int i = ROW_LENGTH - 1, j = 0; i >= 0; i--, j++) {
            if (game_table[i][j] == player_char) {
                diagonal_index++;
            } else if (game_table[i][j] == enemy_char) {
                diagonal_index--;
            } else {
                empty_cell++;
            }
        }

        if (empty_cell > 0) {
            return diagonal_index;
        } else {
            return NO_MORE_EMPTY_CELLS;
        }
    }

    public char switchPlayer(char prev_player) {
        char next_player;
        if (prev_player == 'X') {
            next_player = 'O';
        } else {
            next_player = 'X';
        }
        return next_player;
    }

}
