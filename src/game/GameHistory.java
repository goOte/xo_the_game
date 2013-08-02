package game;

public class GameHistory {

    private final static int ROW_LENGTH = 3;
    private final static int LINE_LENGTH = 3;
    private final static int HISTORY_LENGTH = 10;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;



    private char[][][] game_table_history = new char[HISTORY_LENGTH][ROW_LENGTH][LINE_LENGTH];

    public void writeToHistory(char[][] game_table, int round_number) {

        game_table_history[round_number] = game_table.clone();
    }

    public void showWholeHistory(int round_number) {
        System.out.println("\nВывод истории ходов:");
        for (int i = 0; i < round_number; i++) {
            System.out.println("\nХод №" + (i+ARRAY_ADJUSTMENT_NUMBER));
            showHistory(game_table_history[i+ARRAY_ADJUSTMENT_NUMBER]);
        }

    }

    public char[][] getGameHistoryTable(int round_number) {

        char[][] get_game_history_table = new char[ROW_LENGTH][LINE_LENGTH];
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                get_game_history_table[i][j] = game_table_history[round_number][i][j];
            }
        }
        return get_game_history_table;
    }

    private void showHistory(char[][] game_table) {

        System.out.println();
        for (int i=0; i < ROW_LENGTH; i++) {
            System.out.println(game_table[i][0] + " | " + game_table[i][1] + " | " + game_table[i][2]);
            if (i < ROW_LENGTH -1) System.out.println("---------");
        }
    }

}
