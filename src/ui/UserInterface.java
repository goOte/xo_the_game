package ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

    private final static int MAX_ARRAY_INPUT_NUMBER = 3;
    private final static int MIN_ARRAY_INPUT_NUMBER = 1;
    private final static int ARRAY_ADJUSTMENT_NUMBER = 1;
    private final static int MIN_PLAYERS_NUMBER = 1;
    private final static int MAX_PLAYERS_NUMBER = 2;

    private static final String IP_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public boolean getMode() {

        System.out.println("ОДИН игрок против ROBO (^_^)....\n" +
                "   .....либо ДВА игрока - друг против друга?\n" +
                "\n....Введите число игроков....[1/2]?");

        return getInput(MIN_PLAYERS_NUMBER, MAX_PLAYERS_NUMBER) == MAX_PLAYERS_NUMBER;
    }

    public boolean askYesNo(String message) {
        String userInput;
        boolean answer;

        System.out.println("\n" + message +
                 "\n....Ожидание ввода....[Y/N]  [Д/Н]?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNext()) {
                userInput = scanner.next().toLowerCase();
                if (userInput.equals("y") || userInput.equals("д")) {
                    answer = true;
                    break;
                } else  if(userInput.equals("n") || userInput.equals("н")) {
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

        String userIpInput;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNext()) {
                userIpInput = scanner.next();
                if (ipValidate(userIpInput)) {
                    break;
                } else {
                    System.out.println("Неверный ввод!");
                }
            } else {
                System.out.println("Неверный ввод!");

            }
        }

        return userIpInput;
    }

    public static boolean ipValidate(final String ip){

        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public char getPlayer() {

        String userInput;
        char player;

        System.out.println("Первый игрок выбирает чем ходить!\n" +
                "....Введите.... [X/O]?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNext()) {
                userInput = scanner.next().toLowerCase();
                if (userInput.equals("x") || userInput.equals("х")) {
                    player = 'X';
                    break;
                } else  if(userInput.equals("o") || userInput.equals("0") || userInput.equals("о")) {
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

        int userInput;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput >= min && userInput <= max) {
                    break;
                } else {
                    System.out.println("Неверный ввод!");
                }
            } else {
                System.out.println("Неверный ввод!");

            }
        }
        return userInput;
    }
}
