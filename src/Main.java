import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("--- Игра Крестики-Нолики ---\n");

        GameMechanics newGame = new GameMechanics();
        newGame.startGame();

    }
}
