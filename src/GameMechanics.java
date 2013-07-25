public class GameMechanics {

    private static int MAX_ROUND_NUMBER = 9;

    ArrayMethods gameTable = new ArrayMethods();
    UserInterface userInterface = new UserInterface();

    public void startGame() {
        gameTable.clearGameTable();
        System.out.println("    ---Новая игра!---\n");

        if (userInterface.getMode()) {
            System.out.println("Игра с другом\n");
            humanVsHuman();
        } else {
            System.out.println("Игра против ROBO (*.*)\n");
            humanVsRobo();
        }
    }

    public void humanVsRobo() {

        char player = userInterface.getPlayer();
        int round_number = 1;

        while (true) {

            playerMove(player);
            if (checkRules(player, round_number)) {
                break;
            }
            player = switchPlayer(player);
            round_number++;
            roboMove(player);
            if (checkRules(player, round_number)) {
                break;
            }
            player = switchPlayer(player);
            round_number++;
        }

    }

    public void humanVsHuman() {

        char player = userInterface.getPlayer();
        int round_number = 1;

        while (true) {

            playerMove(player);
            if (checkRules(player, round_number)) {
                break;
            }
            player = switchPlayer(player);
            round_number++;
        }

    }

    private void roboMove(char player) {
        System.out.println("\n.....ROBO [" + player + "] сделал свой ход! (о_-).....\n");
        gameTable.useRoboBrain(player);
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

    private boolean checkRules(char player, int round_number) {

        if (gameTable.checkGameTable(player)) {
            gameTable.showGameTable();
            System.out.println("Игрок [" + player + "] выиграл!");
            return true;
        } else if (round_number == MAX_ROUND_NUMBER) {
            gameTable.showGameTable();
            System.out.println("Ничья!");
            return true;
        } else {
            return false;
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
