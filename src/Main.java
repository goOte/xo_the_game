import game.GameMechanics;

public class Main {

    public static void main(String[] args) {

        System.out.println("--- Игра Крестики-Нолики ---\n");

        GameMechanics newGame = new GameMechanics();
        newGame.startGame();

        System.out.println("\nСпасибо за игру! " +
                "\n\nПрошу писать о найденных ошибках, либо по другим вопросам, на:\n\n" +
                "*** o.shliama@gmail.com ***");

    }
}
