package ui;

import java.util.Scanner;

public class UserInterface {

    private final static int MAX_ARRAY_INPUT_NUMBER = 3;
    private final static int MIN_ARRAY_INPUT_NUMBER = 1;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;
    private final static int MIN_PLAYERS_NUMBER = 1;
    private final static int MAX_PLAYERS_NUMBER = 2;

    public boolean getMode() {

        System.out.println("ОДИН игрок против ROBO (^_^)....\n" +
                "   .....либо ДВА игрока - друг против друга?\n" +
                "\n....Введите число игроков....[1/2]?");

        return getInput(MIN_PLAYERS_NUMBER, MAX_PLAYERS_NUMBER) == MAX_PLAYERS_NUMBER;
    }

    public boolean askYesNo(String message) {
        String user_input;
        boolean answer;

        System.out.println("\n" + message +
                 "\n....Ожидание ввода....[Y/N]  [Д/Н]?");

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(sc.hasNext()) {
                user_input = sc.next().toLowerCase();
                if (user_input.equals("y") || user_input.equals("д")) {
                    answer = true;
                    break;
                } else  if(user_input.equals("n") || user_input.equals("н")) {
                    answer = false;
                    break;
                } else {
                    System.out.println("Введите [Y/N] или [Д/Н]");
                }
            } else {
                System.out.println("Неверный ввод!");
            }
        }

        return answer;
    }

    public String getIpAddress() {

        String user_ip_input;

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(sc.hasNext()) {
                user_ip_input = sc.next();
                break;
            } else {
                System.out.println("Неверный ввод!");

            }
        }

        return user_ip_input;
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
                if (user_input.equals("x") || user_input.equals("х")) {
                    player = 'X';
                    break;
                } else  if(user_input.equals("o") || user_input.equals("0") || user_input.equals("о")) {
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

        System.out.println("Введите номер ячейки по вертикали:");

        return getInput(MIN_ARRAY_INPUT_NUMBER, MAX_ARRAY_INPUT_NUMBER) - ARRAY_ADJUSTMENT_NUMBER;
    }

    public int getLineInput() {

        System.out.println("Введите номер ячейки по горизонтали:");

        return getInput(MIN_ARRAY_INPUT_NUMBER, MAX_ARRAY_INPUT_NUMBER) - ARRAY_ADJUSTMENT_NUMBER;
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
