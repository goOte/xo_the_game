package game;

public class GameHistory {

    private final static int ROW_LENGTH = 3;
    private final static int LINE_LENGTH = 3;
    private final static int HISTORY_LENGTH = 10;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;

    private char[][][] gameTableHistory = new char[HISTORY_LENGTH][ROW_LENGTH][LINE_LENGTH];

    public void writeToHistory(char[][] gameTable, int roundNumber) {

        gameTableHistory[roundNumber] = gameTable.clone();
    }

    public void showWholeHistory(int roundNumber) {
        System.out.println("\nВывод истории ходов:");
        for (int i = 0; i < roundNumber; i++) {
            System.out.println("\nХод №" + (i+ARRAY_ADJUSTMENT_NUMBER));
            showHistory(gameTableHistory[i+ARRAY_ADJUSTMENT_NUMBER]);
        }

    }

    public char[][] getGameHistoryTable(int roundNumber) {

        char[][] getGameHistoryTable = new char[ROW_LENGTH][LINE_LENGTH];
        for (int i=0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                getGameHistoryTable[i][j] = gameTableHistory[roundNumber][i][j];
            }
        }
        return getGameHistoryTable;
    }

    private void showHistory(char[][] gameTable) {

        System.out.println();
        for (int i=0; i < ROW_LENGTH; i++) {
            System.out.println(gameTable[i][0] + " | " + gameTable[i][1] + " | " + gameTable[i][2]);
            if (i < ROW_LENGTH -1) System.out.println("---------");
        }
    }

}
