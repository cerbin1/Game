import app.game.Game;
import app.game.GameBuilder;
import app.game.Player;
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
import org.junit.Test;

import static app.game.token.TokenColor.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameTest {
    private Player player1 = new Player(new Tokens(4, 2, 0, 1, 0));
    private Player player2 = new Player(new Tokens(1, 1, 0, 0, 0));
    private Card cheapCard1 = new CheapCard();
    private Card cheapCard2 = new CheapCard();
    private Card cheapCard3 = new CheapCard(new Tokens(5, 3, 1, 0, 0), 0, Green);
    private Card cheapCard4 = new CheapCard(new Tokens(), 0, Green);
    private Card cheapCard5 = new CheapCard(new Tokens(4, 1, 1, 0, 0), 0, Green);
    private Card cheapCard6 = new CheapCard(new Tokens(), 0, Purple);
    private Card cheapCard7 = new CheapCard(new Tokens(), 0, Blue);
    private Card mediumCard1 = new MediumCard();
    private Card mediumCard2 = new MediumCard(new Tokens(3, 2, 0, 0, 0), 0, Green);
    private Card expensiveCard1 = new ExpensiveCard(new Tokens(4, 4, 3, 0, 0), 0, Green);
    private Card expensiveCard2 = new ExpensiveCard();
    private Card notIncludedCard = new CheapCard();
    private Tokens gameTokens = new Tokens(7, 5);
    private Nobility nobility1 = new Nobility(new Tokens(), 0);
    private Nobility nobility2 = new Nobility(new Tokens(), 0);
    private Nobility nobility3 = new Nobility(new Tokens(), 0);

    @Test
    public void shouldInitializeGame() {
        // given
        GameBuilder builder = gameBuilder();

        // when
        Game game = builder.create();

        // then
        assertEquals(game.getPlayers(), asList(player1, player2));
        assertEquals(game.getAvailableCards(), asList(cheapCard1, cheapCard2, cheapCard3, cheapCard4, cheapCard5, cheapCard6, cheapCard7, mediumCard1, mediumCard2, expensiveCard1, expensiveCard2));
        assertEquals(game.getNobility(), asList(nobility1, nobility2, nobility3));
        assertEquals(game.getTokens(), gameTokens);
        assertFalse(player1.hasCards());
        assertFalse(player2.hasCards());
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
        assertTrue(cheapCard2.isReserved());
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(player1.getCards().get(0), cheapCard2);
        assertEquals(player1.getTokens().getVersatile(), 1);
    }

    @Test
    public void shouldBuyCardAndLoseTokens() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new BuyCardTurn(mediumCard2));

        // then
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(game.getTokens().getGreen(), 10);
        assertEquals(game.getTokens().getPurple(), 9);
        assertEquals(player1.getTokens().getGreen(), 1);
        assertEquals(player1.getTokens().getPurple(), 0);
        assertEquals(player1.getCards().get(0), mediumCard2);
        assertFalse(mediumCard2.isReserved());
        assertFalse(game.getAvailableCards().contains(mediumCard2));
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
    public void shouldBuyCardIncludingBoughtCards() {
        // given
        Game game = gameBuilder().create();
        player1.addCard(cheapCard4);
        player1.addCard(cheapCard6);
        player1.addCard(cheapCard7);

        // when
        game.performTurn(new BuyCardTurn(cheapCard3));

        // then
        assertTrue(player1.getCards().contains(cheapCard3));
        assertFalse(game.getAvailableCards().contains(cheapCard3));
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(player1.getCards(), asList(cheapCard4, cheapCard6, cheapCard7, cheapCard3));
        assertEquals(player1.getTokens().getGreen(), 0);
        assertEquals(player1.getTokens().getPurple(), 0);
        assertEquals(player1.getTokens().getBlue(), 0);
        assertEquals(player1.getTokens().getBlack(), 1);
    }

    @Test
    public void shouldBuyCardOnlyWithBoughtCards() {
        // given
        Game game = gameBuilder().create();
        player1.setTokens(new Tokens());
        player1.addCard(cheapCard3);
        player1.addCard(cheapCard4);
        player1.addCard(cheapCard6);
        player1.addCard(cheapCard7);
        player1.addCard(mediumCard1);
        player1.addCard(expensiveCard1);
        int[] playerTokens = {player1.getTokens().getGreen(), player1.getTokens().getPurple(), player1.getTokens().getBlue(), player1.getTokens().getBlack(), player1.getTokens().getRed()};

        // when
        game.performTurn(new BuyCardTurn(cheapCard5));

        // then
        assertEquals(game.getCurrentPlayer(), player2);
        assertTrue(player1.getCards().contains(cheapCard5));
        assertEquals(player1.getCards(), asList(cheapCard3, cheapCard4, cheapCard6, cheapCard7, mediumCard1, expensiveCard1, cheapCard5));
        assertArrayEquals(playerTokens, new int[]{0, 0, 0, 0, 0});
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

    @Test
    public void shouldThrowOnNotAvailableCard() {
        // given
        Game game = gameBuilder().create();

        // when
        try {
            game.performTurn(new BuyCardTurn(notIncludedCard));
            assertTrue(false);
        }

        // then
        catch (IllegalTurnException exception) {
            assertTrue(true);
        }
    }

    private GameBuilder gameBuilder() {
        GameBuilder builder = new GameBuilder();
        builder.setTokens(gameTokens);
        builder.addPlayer(player1);
        builder.addPlayer(player2);
        builder.addCard(cheapCard1);
        builder.addCard(cheapCard2);
        builder.addCard(cheapCard3);
        builder.addCard(cheapCard4);
        builder.addCard(cheapCard5);
        builder.addCard(cheapCard6);
        builder.addCard(cheapCard7);
        builder.addCard(mediumCard1);
        builder.addCard(mediumCard2);
        builder.addCard(expensiveCard1);
        builder.addCard(expensiveCard2);
        builder.addNobility(nobility1);
        builder.addNobility(nobility2);
        builder.addNobility(nobility3);
        return builder;
    }
}
