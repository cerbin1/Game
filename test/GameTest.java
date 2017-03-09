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
    private Card cheapCard4 = new CheapCard(new Tokens(4, 2, 0, 1, 0), 0, Green);
    private Card cheapCard5 = new CheapCard(new Tokens(4, 1, 1, 0, 0), 0, Green);
    private Card cheapCard6 = new CheapCard(new Tokens(2, 2, 0, 0, 0), 0, Purple);
    private Card cheapCard7 = new CheapCard(new Tokens(), 0, Blue);
    private Card cheapCard8 = new CheapCard(new Tokens(2, 0, 0, 0, 0), 0, Blue);
    private Card mediumCard1 = new MediumCard(new Tokens(2, 2, 2, 2, 2), 0, Red);
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
        assertEquals(asList(player1, player2), game.getPlayers());
        assertEquals(asList(cheapCard1, cheapCard2, cheapCard3, cheapCard4, cheapCard5, cheapCard6, cheapCard7, mediumCard1, mediumCard2, expensiveCard1, expensiveCard2), game.getAvailableCards());
        assertEquals(asList(nobility1, nobility2, nobility3), game.getNobility());
        assertEquals(gameTokens, game.getTokens());
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
        assertEquals(player1, currentPlayer);
    }

    @Test
    public void shouldChangeCurrentPlayerToNext() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new PassTurn());

        // then
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void shouldGetBackToPlayerAfterTwoTurns() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new PassTurn());
        game.performTurn(new PassTurn());

        // then
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void shouldHaveCardAndVersatileToken() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new ReservationTurn(cheapCard2));

        // then
        assertTrue(cheapCard2.isReserved());
        assertEquals(player2, game.getCurrentPlayer());
        assertEquals(cheapCard2, player1.getCards().get(0));
        assertEquals(1, player1.getTokens().getVersatile());
    }

    @Test
    public void shouldRemoveCardFromGameCardsAfterReservation() {
        // given
        Game game = gameBuilder().create();
        cheapCard5.setReserved(true);

        // when
        game.performTurn(new ReservationTurn(cheapCard5));

        // then
        assertFalse(game.getAvailableCards().contains(cheapCard5));
    }

    @Test
    public void shouldRemoveVersatileTokenFromGameAfterReservation() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new ReservationTurn(cheapCard2));

        // then
        assertEquals(new Tokens(7, 4), game.getTokens());
    }

    @Test
    public void shouldBuyCardAndLoseTokens() {
        // given
        Game game = gameBuilder().create();

        // when
        game.performTurn(new BuyCardTurn(mediumCard2));

        // then
        assertEquals(player2, game.getCurrentPlayer());
        assertEquals(new Tokens(10, 9, 7, 7, 7, 5), game.getTokens());
        assertEquals(new Tokens(1, 0, 0, 1, 0), player1.getTokens());
        assertFalse(mediumCard2.isReserved());
        assertFalse(game.getAvailableCards().contains(mediumCard2));
        assertTrue(player1.getCards().contains(mediumCard2));
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
    public void shouldBuyCardForVersatile() {
        // given
        Game game = gameBuilder().create();
        player1.setTokens(new Tokens(0, 0, 0, 0, 0, 4));

        // when
        game.performTurn(new BuyCardTurn(cheapCard6));

        // then
        assertEquals(new Tokens(0, 0, 0, 0, 0, 0), player1.getTokens());
        assertEquals(new Tokens(9, 9, 7, 7, 7, 9), game.getTokens());
        assertTrue(player1.getCards().contains(cheapCard6));
        assertFalse(cheapCard6.isReserved());
    }

    @Test
    public void shouldBuyCardWithBoughtCardsAndVersatile() {
        // given
        Game game = gameBuilder().create();
        player1.setTokens(new Tokens(0, 0, 0, 0, 0, 3));
        player1.addCard(cheapCard3);
        player1.addCard(cheapCard4);
        player1.addCard(mediumCard2);

        // when
        game.performTurn(new BuyCardTurn(cheapCard5));

        // then
        assertEquals(new Tokens(0, 0, 0, 0, 0, 0), player1.getTokens());
        assertEquals(new Tokens(11, 8, 8, 7, 7, 8), game.getTokens());
        assertEquals(asList(cheapCard3, cheapCard4, mediumCard2, cheapCard5), player1.getCards());
        assertFalse(cheapCard5.isReserved());
    }

    @Test
    public void shouldBuyCardWithAllCapabilities() {
        // given
        Game game = gameBuilder().create();
        player1.setTokens(new Tokens(1, 1, 1, 1, 0, 3));
        player1.addCard(cheapCard3);
        player1.addCard(cheapCard6);
        player1.addCard(cheapCard7);

        // when
        game.performTurn(new BuyCardTurn(mediumCard1));

        // then
        assertEquals(new Tokens(0, 0, 0, 0, 0, 0), player1.getTokens());
        assertEquals(new Tokens(9, 9, 9, 9, 9, 8), game.getTokens());
        assertEquals(asList(cheapCard3, cheapCard6, cheapCard7, mediumCard1), player1.getCards());
        assertFalse(mediumCard1.isReserved());
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
        assertEquals(player2, game.getCurrentPlayer());
        assertEquals(new Tokens(0, 0, 0, 1, 0), player1.getTokens());
        assertEquals(new Tokens(12, 10, 8, 7, 7, 5), game.getTokens());
        assertEquals(asList(cheapCard4, cheapCard6, cheapCard7, cheapCard3), player1.getCards());
        assertFalse(game.getAvailableCards().contains(cheapCard3));
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
        player1.addCard(mediumCard2);
        player1.addCard(expensiveCard1);

        // when
        game.performTurn(new BuyCardTurn(cheapCard5));

        // then
        assertEquals(asList(cheapCard3, cheapCard4, cheapCard6, cheapCard7, mediumCard2, expensiveCard1, cheapCard5), player1.getCards());
        assertEquals(new Tokens(0, 0, 0, 0, 0), player1.getTokens());
        assertEquals(new Tokens(11, 8, 8, 7, 7, 5), game.getTokens());
        assertFalse(cheapCard5.isReserved());
    }

    @Test
    public void shouldNotLoseVersatileTokens() {
        // given
        Game game = gameBuilder().create();
        player1.setVersatile(2);

        // when
        game.performTurn(new BuyCardTurn(cheapCard4));

        // then
        assertEquals(new Tokens(0, 0, 0, 0, 0, 2), player1.getTokens());
    }

    @Test
    public void shouldAssignTokensAfterBuyingReservedCard() {
        // given
        Game game = gameBuilder().create();
        cheapCard6.setReserved(true);

        // when
        game.performTurn(new BuyCardTurn(cheapCard6));

        // then
        assertEquals(new Tokens(2, 0, 0, 1, 0), player1.getTokens());
        assertEquals(new Tokens(9, 9, 7, 7, 7, 5), game.getTokens());
    }

    @Test
    public void shouldRemoveReservationAfterBuyingReservedCard() {
        // given
        Game game = gameBuilder().create();
        cheapCard8.setReserved(true);
        player1.addCard(cheapCard8);

        // when
        game.performTurn(new BuyCardTurn(cheapCard8));

        // then
        assertFalse(cheapCard8.isReserved());
    }

    @Test
    public void shouldThrowOnBuyingNotAvailableCard() {
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
