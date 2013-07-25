import java.util.Scanner;

public class UserInterface {

    public final static int MAX_ARRAY_INPUT_NUMBER = 3;
    public final static int MIN_ARRAY_INPUT_NUMBER = 1;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;
    private final static int MIN_PLAYERS_NUMBER = 1;
    private final static int MAX_PLAYERS_NUMBER = 2;

    public boolean getMode() {
        int user_players_number_input;
        System.out.println("ОДИН игрок против ROBO (^_^)....\n" +
                "   .....либо ДВА игрока - друг против друга?\n" +
                "\n....Введите число игроков....[1/2]?");
        if (getInput(MIN_PLAYERS_NUMBER, MAX_PLAYERS_NUMBER) == MAX_PLAYERS_NUMBER) {
            return true;
        } else {
            return false;
        }
    }

    public char getPlayer() {

        String user_input;
        char player;

        System.out.println("Первый игрок выбирает чем ходить!\n" +
                "....Введите.... [X/O]?");

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(sc.hasNext()) {
                user_input = sc.next().toLowerCase();
                if (user_input.equals("x")) {
                    player = 'X';
                    break;
                } else  if(user_input.equals("o") || user_input.equals("0")) {
                    player = 'O';
                    break;
                } else {
                    System.out.println("Введите Х или О");
                }
            } else {
                System.out.println("Неверный ввод!");

            }
        }
        return player;
    }

    public int getRowInput() {
        int user_row_input;
        System.out.println("Введите номер ячейки по вертикали:");
        return user_row_input = getInput(MIN_ARRAY_INPUT_NUMBER, MAX_ARRAY_INPUT_NUMBER) - ARRAY_ADJUSTMENT_NUMBER;
    }

    public int getLineInput() {
        int user_line_input;
        System.out.println("Введите номер ячейки по горизонтали:");
        return user_line_input = getInput(MIN_ARRAY_INPUT_NUMBER, MAX_ARRAY_INPUT_NUMBER) - ARRAY_ADJUSTMENT_NUMBER;
    }

    public int getInput(int min, int max) {

        int user_input;

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(sc.hasNextInt()) {
                user_input = sc.nextInt();
                if (user_input >= min && user_input <= max) {
                    break;
                } else {
                    System.out.println("Неверный ввод!");
                }
            } else {
                System.out.println("Неверный ввод!");

            }
        }
        return user_input;
    }
}
