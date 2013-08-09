package game;

import lan.*;
import ui.*;

public class GameMechanics {

    final static int MAX_ROUND_NUMBER = 9;

    ArrayMethods gameTable = new ArrayMethods();
    UserInterface userInterface = new UserInterface();
    InfoSender infoSender = new InfoSender();
    InfoReceiver infoReceiver = new InfoReceiver();
    GameHistory gameHistory = new GameHistory();


    public void startGame() {
        gameTable.clearGameTable();
        System.out.println("    ---Новая игра!---\n");

        if (userInterface.getMode()) {
            System.out.println("Игра с другом\n");
            if (userInterface.askYesNo("Хотите сыграть по сети?....")) {
                System.out.println("Игра по сети\n");
                lanHumanVsHuman();
            } else {
                System.out.println("Игра на одном ПК\n");
                humanVsHuman();
            }
        } else {
            System.out.println("Игра против ROBO (*.*)\n");
            humanVsRobo();
        }

        if (userInterface.askYesNo("Хотите сыграть еще?....")) {
            startGame();
        }

    }

    public void lanHumanVsHuman() {

        if (userInterface.askYesNo("Хотите создать игру?....")) {
            System.out.println("Вы хост игры, узнайте IP-адрес вашего соперника\n");
            hostGame();
        } else {
            System.out.printf("\nОжидайте, пока хост введет ваш IP-адрес (%s), и создаст игру....\n", infoSender.getLocalIpAddress());
            nonHostGame();
        }

    }

    public void humanVsRobo() {

        char player = userInterface.getPlayer();
        int roundNumber = 0;
        gameHistory.writeToHistory(gameTable.getGameTable(), roundNumber);

        while (true) {

            roundNumber++;

            playerMove(player, roundNumber);
            if (checkRules(player, roundNumber)) {
                break;
            }

            player = gameTable.switchPlayer(player);
            roundNumber++;

            roboMove(player, roundNumber);
            if (checkRules(player, roundNumber)) {
                break;
            }

            player = gameTable.switchPlayer(player);

            if (userInterface.askYesNo("Хотите вернуться на ход назад?")) {
                gameTable.setGameTable(gameHistory.getGameHistoryTable(roundNumber - 2));
                roundNumber = roundNumber -2;
            }
        }

        if (userInterface.askYesNo("Хотите просмотреть историю ходов?")) {
            gameHistory.showWholeHistory(roundNumber);
        }

    }

    public void humanVsHuman() {

        char player = userInterface.getPlayer();
        int roundNumber = 1;

        while (true) {

            playerMove(player, roundNumber);
            if (checkRules(player, roundNumber)) {
                break;
            }
            player = gameTable.switchPlayer(player);
            roundNumber++;
        }

    }

    public void hostGame() {

        System.out.println("Введите IP-адрес соперника\n");
        String enemyIpAddress = userInterface.getIpAddress();
        infoSender.SendInfoIpAddress(enemyIpAddress);

        System.out.println("Игра создана...\n");
        String[] playerMovePack;
        char player = userInterface.getPlayer();
        char enemy = gameTable.switchPlayer(player);
        char playerChar;
        int row, line;
        infoSender.SendInfoPlayerChar(enemyIpAddress, player);
        int roundNumber = 1;

        while (true) {

            lanPlayerMove(player, enemyIpAddress);
            if (checkRules(player, roundNumber)) {
                break;
            }
            gameTable.showGameTable();
            roundNumber++;

            playerMovePack = infoReceiver.ReceiveInfoPlayerMove();
            playerChar = playerMovePack[0].charAt(0);
            row = Integer.parseInt(playerMovePack[1]);
            line = Integer.parseInt(playerMovePack[2]);
            gameTable.acceptUserInput(playerChar, row, line);

            if (checkRules(enemy, roundNumber)) {
                break;
            } else {
                System.out.printf("\n\nПротивник [%s] сделал ход\n", enemy);
            }
            roundNumber++;
        }

    }

    public void nonHostGame() {

        String enemyIpAddress = infoReceiver.ReceiveInfoIpAddress();

        System.out.println("IP-адрес получен. Игра создана...\n");
        char enemy = infoReceiver.ReceiveInfoPlayerChar();
        char player = gameTable.switchPlayer(enemy);
        char playerChar;
        int row, line;
        String[] playerMovePack;
        System.out.printf("Соперник выбрал [%s] , соответственно вы ходите [%s]\n", enemy, player);
        int roundNumber = 1;

        while (true) {

            playerMovePack = infoReceiver.ReceiveInfoPlayerMove();
            playerChar = playerMovePack[0].charAt(0);
            row = Integer.parseInt(playerMovePack[1]);
            line = Integer.parseInt(playerMovePack[2]);
            gameTable.acceptUserInput(playerChar, row, line);


            if (checkRules(enemy, roundNumber)) {
                break;
            } else {
                System.out.printf("\nПротивник [%s] сделал ход\n", enemy);
            }
            roundNumber++;

            lanPlayerMove(player, enemyIpAddress);
            if (checkRules(player, roundNumber)) {
                break;
            }
            gameTable.showGameTable();
            roundNumber++;
        }
    }

    private void roboMove(char player, int roundNumber) {
        System.out.printf("\n.....ROBO [%s] сделал свой ход! (о_-).....\n", player);
        gameTable.useRoboBrain(player);
        gameHistory.writeToHistory(gameTable.getGameTable(), roundNumber);
        gameTable.showGameTable();
    }

    private void playerMove(char player, int roundNumber) {

        System.out.printf("\nХод игрока [%s]\n", player);

        while (true) {

            gameTable.showGameTable();
            int rowInput = userInterface.getRowInput();
            int lineInput = userInterface.getLineInput();

            if (gameTable.checkUserInput(rowInput, lineInput)) {

                gameTable.acceptUserInput(player, rowInput, lineInput);
                gameHistory.writeToHistory(gameTable.getGameTable(), roundNumber);
                System.out.printf("\nИгрок [%s] сделал совой ход\n", player);
                gameTable.showGameTable();
                break;
            }
        }
    }

    private void lanPlayerMove(char player, String ipAddress) {

        System.out.printf("Ход игрока [%s]\n", player);

        while (true) {

            gameTable.showGameTable();
            int rowInput = userInterface.getRowInput();
            int lineInput = userInterface.getLineInput();

            if (gameTable.checkUserInput(rowInput, lineInput)) {

                gameTable.acceptUserInput(player, rowInput, lineInput);
                infoSender.SendInfoPlayerMove(ipAddress, player, rowInput, lineInput);
                break;
            }
        }
    }

    private boolean checkRules(char player, int roundNumber) {

        if (gameTable.checkGameTable(player)) {
            System.out.printf("Игрок [%s] выиграл!\n", player);
            return true;
        } else if (roundNumber == MAX_ROUND_NUMBER) {
            gameTable.showGameTable();
            System.out.println("Ничья!");
            return true;
        } else {
            return false;
        }
    }

}
