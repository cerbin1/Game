import app.game.*;
import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.card.ExpensiveCard;
import app.game.card.MediumCard;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import app.game.turn.BuyCardTurn;
import app.game.turn.IllegalTurnException;
import app.game.turn.PassTurn;
import app.game.turn.ReservationTurn;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameTest {
    private Player player1 = new Player(new Tokens(3, 0, 0, 0, 0));
    private Player player2 = new Player(new Tokens(1, 3));
    private Card cheapCard1 = new CheapCard();
    private Card cheapCard2 = new CheapCard();
    private Card cheapCard3 = new CheapCard();
    private Card cheapCard4 = new CheapCard();
    private Card cheapCard5 = new CheapCard();
    private Card mediumCard1 = new MediumCard();
    private Card mediumCard2 = new MediumCard();
    private Card expensiveCard1 = new ExpensiveCard();
    private Card expensiveCard2 = new ExpensiveCard();
    private Tokens gameTokens = new Tokens(7, 5);
    private Nobility nobility1 = new Nobility();
    private Nobility nobility2 = new Nobility();
    private Nobility nobility3 = new Nobility();

    @Test
    public void shouldInitializeGame() {
        // given
        GameBuilder builder = gameBuilder();

        // when
        Game game = builder.create();

        // then
        assertEquals(game.getPlayers(), asList(player1, player2));
        assertEquals(game.getCheapCards(), asList(cheapCard1, cheapCard2, cheapCard3, cheapCard4, cheapCard5));
        assertEquals(game.getMediumCards(), asList(mediumCard1, mediumCard2));
        assertEquals(game.getExpensiveCards(), asList(expensiveCard1, expensiveCard2));
        assertEquals(game.getNobility(), asList(nobility1, nobility2, nobility3));
        assertEquals(game.getTokens(), gameTokens);
        assertTrue(player1.getCards().isEmpty());
        assertTrue(player2.getCards().isEmpty());
    }

    @Test
    public void shouldHaveFirstPlayer() {
        // given
        Game game = gameBuilder().create();

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        assertEquals(currentPlayer, player1);
    }

    @Test
    public void shouldChangeCurrentPlayerToNext() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new PassTurn());

        // then
        assertEquals(game.getCurrentPlayer(), player2);
    }

    @Test
    public void shouldGetBackToPlayerAfterTwoTurns() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new PassTurn());
        game.performTurn(new PassTurn());

        // then
        assertEquals(game.getCurrentPlayer(), player1);
    }

    @Test
    public void shouldHaveCardAndVersatileToken() {
        // given
        Game game = gameBuilder().create();

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
        Game game = gameBuilder().create();

        // when
        game.performTurn(new BuyCardTurn(mediumCard2));

        // then
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(game.getTokens().getGreen(), 9);
        assertEquals(player1.getTokens().getGreen(), 1);
        assertEquals(player1.getCards().get(0), mediumCard2);
        assertFalse(mediumCard1.isReserved());
    }

    @Test
    public void shouldThrowOnBuyingNotAvailableCard() {
        // given
        Game game = gameBuilder().create();

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
        Game game = gameBuilder().create();

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
        Game game = gameBuilder().create();
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
        Game game = gameBuilder().create();
        game.performTurn(new PassTurn());

        // when
        game.performTurn(new BuyCardTurn(cheapCard1));

        // then
        assertEquals(player2.getTokens().getGreen(), 0);
        assertEquals(player2.getTokens().getVersatile(), 3);
    }

    private GameBuilder gameBuilder() {
        GameBuilder builder = new GameBuilder();
        builder.setTokens(gameTokens);
        builder.addPlayer(player1);
        builder.addPlayer(player2);
        builder.addCheapCard(cheapCard1);
        builder.addCheapCard(cheapCard2);
        builder.addCheapCard(cheapCard3);
        builder.addCheapCard(cheapCard4);
        builder.addCheapCard(cheapCard5);
        builder.addMediumCard(mediumCard1);
        builder.addMediumCard(mediumCard2);
        builder.addExpensiveCard(expensiveCard1);
        builder.addExpensiveCard(expensiveCard2);
        builder.addNobilityCard(nobility1);
        builder.addNobilityCard(nobility2);
        builder.addNobilityCard(nobility3);
        return builder;
    }
}
