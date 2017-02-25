package game;

public class Game {
    static Tokens tokens = new Tokens(7, 5);

    public static void main(String[] args) {
        Player player = new Player(new Tokens(7, 5));
    }
}
