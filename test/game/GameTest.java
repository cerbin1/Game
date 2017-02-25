package game;

import org.junit.Test;

import static java.util.Arrays.asList;

public class GameTest {

    @Test
    public void shouldInitializeGame() {
        // given
        GameFactory factory = new GameFactory();

        Player player1 = new Player(new Tokens());
        Player player2 = new Player(new Tokens());
        Card cheapCard1 = new CheapCard();
        Card cheapCard2 = new CheapCard();
        Card mediumCard1 = new MediumCard();
        Card mediumCard2 = new MediumCard();
        Card expensiveCard1 = new ExpensiveCard();
        Card expensiveCard2 = new ExpensiveCard();

        factory.addPlayer(player1);
        factory.addPlayer(player2);
        factory.addCard(cheapCard1);
        factory.addCard(cheapCard2);
        factory.addCard(mediumCard2);
        factory.addCard(mediumCard1);
        factory.addCard(expensiveCard1);
        factory.addCard(expensiveCard2);

        // when
        Game game = factory.create();

        // then
        assertEquals(game.getPayers(), asList(player1, player2));
        assertEquals(game.getCheapCards(), asList(cheapCard1, cheapCard2));
        assertEquals(game.getRegularCards(), asList(mediumCard1, mediumCard2));
        assertEquals(game.getExpensiveCards(), asList(expensiveCard2, expensiveCard2));
    }
}