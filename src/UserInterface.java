import java.util.Scanner;

public class UserInterface {

    public final static int MAX_INPUT_NUMBER = 3;
    public final static int MIN_INPUT_NUMBER = 1;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;

    public char getPlayer() {

        String user_input;
        char player;

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
        return user_row_input = getInput() - ARRAY_ADJUSTMENT_NUMBER;
    }

    public int getLineInput() {
        int user_line_input;
        System.out.println("Введите номер ячейки по горизонтали:");
        return user_line_input = getInput() - ARRAY_ADJUSTMENT_NUMBER;
    }

    public int getInput() {

        int user_input;

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(sc.hasNextInt()) {
                user_input = sc.nextInt();
                if (user_input >= MIN_INPUT_NUMBER && user_input <= MAX_INPUT_NUMBER) {
                    break;
                } else {
                    System.out.println("Введите число от 1 до 3");
                }
            } else {
                System.out.println("Неверный ввод!");

            }
        }
        return user_input;
    }
}
