import java.io.IOException;

public class GameMechanics {

    ArrayMethods gameTable = new ArrayMethods();
    UserInterface userInterface = new UserInterface();
    InfoSender infoSender = new InfoSender();
    InfoReceiver infoReceiver = new InfoReceiver();
    GameHistory gameHistory = new GameHistory();


    public void startGame() throws IOException {
        gameTable.clearGameTable();
        System.out.println("    ---Новая игра!---\n");

        if (userInterface.getMode()) {
            System.out.println("Игра с другом\n");
            if (userInterface.getLanMode()) {
                System.out.println("Игра по сети\n");
                lanHumanVsHuman();
            } else {
                System.out.println("Игра на одном ПК\n");
                humanVsHuman();
                if (userInterface.toContinue()) {
                    startGame();
                }
            }
        } else {
            System.out.println("Игра против ROBO (*.*)\n");
            humanVsRobo();
            if (userInterface.toContinue()) {
                startGame();
            }
        }

    }

    public void lanHumanVsHuman() throws IOException {

        if (userInterface.getHost()) {
            System.out.println("Вы хост игры, узнайте IP-адрес вашего соперника\n");
            hostGame();
        } else {
            System.out.println("Ожидайте, пока хост введет ваш IP-адрес\n");
            nonHostGame();
        }

    }

    public void humanVsRobo() {

        char player = userInterface.getPlayer();
        int round_number = 0;
        gameHistory.writeToHistory(gameTable.getGameTable(), round_number);

        while (true) {

            round_number++;

            playerMove(player, round_number);
            if (checkRules(player, round_number)) {
                break;
            }

            player = switchPlayer(player);
            round_number++;

            roboMove(player, round_number);
            if (checkRules(player, round_number)) {
                break;
            }

            player = switchPlayer(player);

            if (userInterface.askToMoveBack()) {
                gameTable.setGame_table(gameHistory.getGameHistoryTable(round_number-2));
                round_number = round_number -2;
            }
        }

        if (userInterface.askToShowHistory()) {
            gameHistory.showWholeHistory(round_number);
        }

    }

    public void humanVsHuman() {

        char player = userInterface.getPlayer();
        int round_number = 1;

        while (true) {

            playerMove(player, round_number);
            if (checkRules(player, round_number)) {
                break;
            }
            player = switchPlayer(player);
            round_number++;
        }

    }

    public void hostGame() throws IOException {

        System.out.println("Введите ваш IP-адрес\n");
        String my_ip_address = userInterface.getIpAddress();
        System.out.println("Введите IP-адрес соперника\n");
        String enemy_ip_address = userInterface.getIpAddress();
        infoSender.SendInfoIpAddress(enemy_ip_address, my_ip_address);

        System.out.println("Вы успешно подключились к сопернику!\n");
        String[] player_move_pack;
        char player = userInterface.getPlayer();
        char enemy = switchPlayer(player);
        char test_player;
        int row, line;
        infoSender.SendInfoPlayerChar(enemy_ip_address, player);
        int round_number = 1;

        while (true) {

            lanPlayerMove(player, enemy_ip_address, round_number);
            if (checkRules(player, round_number)) {
                break;
            }
            round_number++;

            player_move_pack = infoReceiver.ReceiveInfoPlayerMove(round_number);
            test_player = player_move_pack[0].charAt(0);
            row = Integer.parseInt(player_move_pack[1]);
            line = Integer.parseInt(player_move_pack[2]);
            gameTable.acceptUserInput(test_player, row, line);

            if (checkRules(enemy, round_number)) {
                break;
            } else {
                System.out.println("\nПротивник [" + enemy + "] сделал ход\n");
            }
            round_number++;
        }

    }

    public void nonHostGame() throws IOException {

        String enemy_ip_address = infoReceiver.ReceiveInfoIpAddress();

        System.out.println("Вы успешно подключились к сопернику!\n");
        char enemy = infoReceiver.ReceiveInfoPlayerChar();
        char player = switchPlayer(enemy);
        char test_player;
        int row, line;
        String[] player_move_pack;
        System.out.println("Соперник выбрал [" + enemy + "] , соответственно вы ходите [" + player + "]\n");
        int round_number = 1;

        while (true) {

            player_move_pack = infoReceiver.ReceiveInfoPlayerMove(round_number);
            test_player = player_move_pack[0].charAt(0);
            row = Integer.parseInt(player_move_pack[1]);
            line = Integer.parseInt(player_move_pack[2]);
            gameTable.acceptUserInput(test_player, row, line);


            if (checkRules(enemy, round_number)) {
                break;
            } else {
                System.out.println("\nПротивник [" + enemy + "] сделал ход\n");
            }
            round_number++;

            lanPlayerMove(player, enemy_ip_address, round_number);
            if (checkRules(player, round_number)) {
                break;
            }
            round_number++;
        }
    }

    private void roboMove(char player, int round_number) {
        System.out.println("\n.....ROBO [" + player + "] сделал свой ход! (о_-).....\n");
        gameTable.useRoboBrain(player);
        gameHistory.writeToHistory(gameTable.getGameTable(), round_number);
        gameTable.showGameTable();
    }

    private void playerMove(char player, int round_number) {

        System.out.println("\nХод игрока [" + player + "]\n");

        while (true) {

            gameTable.showGameTable();
            int row_number = userInterface.getRowInput();
            int line_number = userInterface.getLineInput();

            if (gameTable.checkUserInput(row_number, line_number)) {

                gameTable.acceptUserInput(player, row_number, line_number);
                gameHistory.writeToHistory(gameTable.getGameTable(), round_number);
                System.out.println("\nИгрок [" + player + "] сделал совой ход\n");
                gameTable.showGameTable();
                break;
            }
        }
    }

    private void lanPlayerMove(char player, String ip_address, int port_index) throws IOException {

        System.out.println("Ход игрока [" + player + "]");

        while (true) {

            gameTable.showGameTable();
            int row_number = userInterface.getRowInput();
            int line_number = userInterface.getLineInput();

            if (gameTable.checkUserInput(row_number, line_number)) {

                gameTable.acceptUserInput(player, row_number, line_number);
                infoSender.SendInfoPlayerMove(ip_address, port_index, player, row_number, line_number);
                break;
            }
        }
    }

    private boolean checkRules(char player, int round_number) {

        final int MAX_ROUND_NUMBER = 9;

        if (gameTable.checkGameTable(player)) {
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
