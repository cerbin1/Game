import game.Game;
import game.GameFactory;
import game.Player;
import game.Tokens;
import game.cards.Card;
import game.cards.CheapCard;
import game.cards.ExpensiveCard;
import game.cards.MediumCard;
import game.turn.*;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameTest {
    private Player player1 = new Player(new Tokens(3, 0, 0, 0, 0));
    private Player player2 = new Player(new Tokens(1, 3));
    private Card cheapCard1 = new CheapCard(new Tokens(1, 0, 0, 0, 0));
    private Card cheapCard2 = new CheapCard();
    private Card cheapCard3 = new CheapCard();
    private Card cheapCard4 = new CheapCard();
    private Card cheapCard5 = new CheapCard();
    private Card mediumCard1 = new MediumCard();
    private Card mediumCard2 = new MediumCard(new Tokens(2, 0, 0, 0, 0));
    private Card expensiveCard1 = new ExpensiveCard(new Tokens(4, 0, 0, 0, 0));
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
        assertTrue(player1.getCards().isEmpty());
        assertTrue(player2.getCards().isEmpty());
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
    public void shouldChangeGameAndPlayerTokensAfterAcquireTurn() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new AcquireTokensTurn(new Tokens(1, 0, 0, 1, 1)));

        // then
        assertEquals(gameTokens.getGreen(), 6);
        assertEquals(gameTokens.getBlack(), 6);
        assertEquals(player1.getTokens().getGreen(), 1);
        assertEquals(player1.getTokens().getBlack(), 1);
        assertEquals(game.getCurrentPlayer(), player2);
        assertTrue(player1.getCards().isEmpty());
    }

    @Test
    public void shouldChangeCurrentPlayerToNext() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new PassTurn());

        // then
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
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(player1.getCards().get(0), cheapCard2);
        Assert.assertTrue(cheapCard2.isReserved());
        assertEquals(player1.getTokens().getVersatile(), 1);
    }

    @Test
    public void shouldBuyCardAndLoseToken() {
        // given
        Game game = gameFactory().create();

        // when
        game.performTurn(new BuyCardTurn(mediumCard2));

        // then
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(player1.getTokens().getGreen(), 1);
        assertEquals(player1.getCards().get(0), mediumCard2);
        assertFalse(mediumCard1.isReserved());
    }

    @Test
    public void shouldThrowOnBuyingNotAvailableCard() {
        // given
        Game game = gameFactory().create();

        // when
        try {
            game.performTurn(new BuyCardTurn(cheapCard5));
            assertTrue(false);
        }
        // then
        catch (IllegalTurnException ignored) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowOnBuyingCardThatIsTooExpensive() {
        // given
        Game game = gameFactory().create();

        // when
        try {
            game.performTurn(new BuyCardTurn(expensiveCard1));
            assertTrue(false);
        }
        // then
        catch (IllegalTurnException ignored) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldBuyCardAndLoseVersatileToken() {
        // given
        Game game = gameFactory().create();
        game.performTurn(new PassTurn());

        // when
        game.performTurn(new BuyCardTurn(expensiveCard1));

        // then
        assertEquals(player2.getTokens().getGreen(), 0);
        assertEquals(player2.getTokens().getVersatile(), 0);
        assertEquals(player2.getCards().get(0), expensiveCard1);
        assertFalse(expensiveCard1.isReserved());
    }

    @Test
    public void shouldNotLoseVersatileTokens() {
        // given
        Game game = gameFactory().create();
        game.performTurn(new PassTurn());

        // when
        game.performTurn(new BuyCardTurn(cheapCard1));

        // then
        assertEquals(player2.getTokens().getGreen(), 0);
        assertEquals(player2.getTokens().getVersatile(), 3);
    }

    private GameFactory gameFactory() {
        GameFactory factory = new GameFactory();
        factory.setTokens(gameTokens);
        factory.addPlayer(player1);
        factory.addPlayer(player2);
        factory.addCheapCard(cheapCard1);
        factory.addCheapCard(cheapCard2);
        factory.addCheapCard(cheapCard3);
        factory.addCheapCard(cheapCard4);
        factory.addCheapCard(cheapCard5);
        factory.addMediumCard(mediumCard2);
        factory.addMediumCard(mediumCard1);
        factory.addExpensiveCard(expensiveCard1);
        factory.addExpensiveCard(expensiveCard2);
        return factory;
    }
}
