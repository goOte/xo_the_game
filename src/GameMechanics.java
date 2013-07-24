public class GameMechanics {

    private static String GAME_PLAYING = "Play";
    private static String GAME_OVER = "Over";
    String game_progress = GAME_PLAYING;

    ArrayMethods gameTable = new ArrayMethods();
    UserInterface userInterface = new UserInterface();

    public void startGame() {

        System.out.println("       Новая игра!\n" +
                            "Первый игрок выбирает чем ходить!\n" +
                            "Введите... [X/O]");

        gameTable.clearGameTable();
        char player = userInterface.getPlayer();
        int round_number = 1;

        while (game_progress == GAME_PLAYING) {

            playerMove(player);
            checkRules(player, round_number);
            player = switchPlayer(player);
            round_number++;
        }
    }

    private void playerMove(char player) {

        System.out.println("Ход игрока [" + player + "]");

        while (true) {

            gameTable.showGameTable();
            int row_number = userInterface.getRowInput();
            int line_number = userInterface.getLineInput();

            if (gameTable.checkUserInput(row_number, line_number)) {

                gameTable.acceptUserInput(player, row_number, line_number);
                break;
            }
        }
    }

    private void checkRules(char player, int round_number) {

        if (gameTable.checkGameTable(player)) {
            gameTable.showGameTable();
            System.out.println("Игрок [" + player + "] выиграл!");
            game_progress = GAME_OVER;
        } else if (round_number == 9) {
            gameTable.showGameTable();
            System.out.println("Ничья!");
            game_progress = GAME_OVER;
        }
    }

    private char switchPlayer(char prev_player) {
        char next_player;
        if (prev_player == 'X') {
            next_player = 'O';
        } else {
            next_player = 'X';
        }
        return next_player;
    }
}
