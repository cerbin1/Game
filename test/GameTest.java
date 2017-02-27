import game.*;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;

package test;

public class GameTest {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Card cheapCard1 = new CheapCard();
    private Card cheapCard2 = new CheapCard();
    private Card mediumCard1 = new MediumCard();
    private Card mediumCard2 = new MediumCard();
    private Card expensiveCard1 = new ExpensiveCard();
    private Card expensiveCard2 = new ExpensiveCard();
    private Tokens gameTokens = new Tokens(7, 5);

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
        Assert.assertEquals(game.getTokens(), gameTokens);
    }

    @Test
    public void shouldHaveFirstPlayer() {
        // given
        Game game = gameFactory().create();

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        Assert.assertEquals(currentPlayer, player1);
    }

    @Test
    public void shouldChangePlayerAfterTurn() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new AquireTokensTurn(new Tokens(1, 0, 0, 1, 1)));

        // then
        Assert.assertEquals(gameTokens.getGreen(), 6);
        Assert.assertEquals(gameTokens.getBlack(), 6);
        Assert.assertEquals(player1.getTokens.getGreen(), 1);
        Assert.assertEquals(player1.getTokens.getBlack(), 1);
        Assert.assertEquals(game.getCurrentPlayer(), player2);
    }

    @Test
    public void shouldGetBackToPlayerAfterTwoTurns() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new PassTurn());
        game.performTurn(new PassTurn());

        // then
        Assert.assertEquals(game.getCurrentPlayer(), player1);
    }

    @Test
    public void shouldHaveCardAndVersatileToken() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new ReservationTurn(cheapCard2));

        // then
        Assert.assertEquals(player1.getCards(), asList(cheapCard2));
        Assert.assertTrue(cheapCard2.isReserved());
        Assert.assertEquals(player1.getTokens().getVersatile(), 1);
    }

    private GameFactory gameFactory() {
        GameFactory factory = new GameFactory();
        factory.setTokens(gameTokens);
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