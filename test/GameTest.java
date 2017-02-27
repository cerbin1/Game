import game.*;
import game.cards.*;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

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
        assertEquals(game.getPlayers(), asList(player1, player2));
        assertEquals(game.getCheapCards(), asList(cheapCard1, cheapCard2));
        assertEquals(game.getMediumCards(), asList(mediumCard1, mediumCard2));
        assertEquals(game.getExpensiveCards(), asList(expensiveCard2, expensiveCard2));
        assertEquals(game.getTokens(), gameTokens);
    }

    @Test
    public void shouldHaveFirstPlayer() {
        // given
        Game game = gameFactory().create();

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        assertEquals(currentPlayer, player1);
    }

    @Test
    public void shouldChangePlayerAfterTurn() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new AquireTokensTurn(new Tokens(1, 0, 0, 1, 1)));

        // then
        assertEquals(gameTokens.getGreen(), 6);
        assertEquals(gameTokens.getBlack(), 6);
        assertEquals(player1.getTokens.getGreen(), 1);
        assertEquals(player1.getTokens.getBlack(), 1);
        assertEquals(game.getCurrentPlayer(), player2);
    }

    @Test
    public void shouldGetBackToPlayerAfterTwoTurns() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new PassTurn());
        game.performTurn(new PassTurn());

        // then
        assertEquals(game.getCurrentPlayer(), player1);
    }

    @Test
    public void shouldHaveCardAndVersatileToken() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new ReservationTurn(cheapCard2));

        // then
        assertEquals(player1.getCards(), asList(cheapCard2));
        Assert.assertTrue(cheapCard2.isReserved());
        assertEquals(player1.getTokens().getVersatile(), 1);
    }

    private GameFactory gameFactory() {
        GameFactory factory = new GameFactory();
        factory.setTokens(gameTokens);
        factory.addPlayer(player1);
        factory.addPlayer(player2);
        factory.addCheapCard(cheapCard1);
        factory.addCheapCard(cheapCard2);
        factory.addMediumCard(mediumCard2);
        factory.addMediumCard(mediumCard1);
        factory.addExpensiveCard(expensiveCard1);
        factory.addExpensiveCard(expensiveCard2);
        return factory;
    }
}
