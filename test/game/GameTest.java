package game;

import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;

public class GameTest {
    private Player player1 = new Player(new Tokens());
    private Player player2 = new Player(new Tokens());
    private Card cheapCard1 = new CheapCard();
    private Card cheapCard2 = new CheapCard();
    private Card mediumCard1 = new MediumCard();
    private Card mediumCard2 = new MediumCard();
    private Card expensiveCard1 = new ExpensiveCard();
    private Card expensiveCard2 = new ExpensiveCard();

    @Test
    public void shouldInitializeGame() {
        // given
        GameFactory factory = gameFactory();

        // when
        Game game = factory.create();

        // then
        Assert.assertEquals(game.getPayers(), asList(player1, player2));
        Assert.assertEquals(game.getCheapCards(), asList(cheapCard1, cheapCard2));
        Assert.assertEquals(game.getRegularCards(), asList(mediumCard1, mediumCard2));
        Assert.assertEquals(game.getExpensiveCards(), asList(expensiveCard2, expensiveCard2));
    }

    private GameFactory gameFactory() {
        GameFactory factory = new GameFactory();
        factory.addPlayer(player1);
        factory.addPlayer(player2);
        factory.addCard(cheapCard1);
        factory.addCard(cheapCard2);
        factory.addCard(mediumCard2);
        factory.addCard(mediumCard1);
        factory.addCard(expensiveCard1);
        factory.addCard(expensiveCard2);
        return factory;
    }
}