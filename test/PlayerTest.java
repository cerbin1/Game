import app.game.Player;
import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.card.ExpensiveCard;
import app.game.card.MediumCard;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import org.junit.Test;

import static app.game.token.TokenColor.Green;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    @Test
    public void shouldCreatePlayerWithDefaultTokens() {
        // when
        Player player = new Player(new Tokens(0, 1, 2, 3, 4));

        // then
        assertTrue(new Tokens(0, 1, 2, 3, 4).equals(player.getTokens()));
    }

    @Test
    public void shouldCountPointsFromCards() {
        // given
        Player player = new Player();
        player.addCard(new CheapCard(new Tokens(), 0, Green));
        player.addCard(new CheapCard(new Tokens(), 1, Green));
        player.addCard(new MediumCard(new Tokens(), 2, Green));
        player.addCard(new ExpensiveCard(new Tokens(), 5, Green));

        // when
        int points = player.getPoints();

        // then
        assertEquals(8, points);
    }

    @Test
    public void shouldCountPointsFromReservedCards() {
        // given
        Player player = new Player();
        Card reservedCard1 = new CheapCard(new Tokens(), 1, Green);
        Card reservedCard2 = new MediumCard(new Tokens(), 2, Green);
        Card reservedCard3 = new ExpensiveCard(new Tokens(), 5, Green);
        reservedCard1.setReserved(true);
        reservedCard2.setReserved(true);
        reservedCard3.setReserved(true);
        player.addCard(reservedCard1);
        player.addCard(reservedCard2);
        player.addCard(reservedCard3);

        // when
        int points = player.getPoints();

        // then
        assertEquals(0, points);
    }

    @Test
    public void shouldCountPointsFromNobilities() {
        // given
        Player player = new Player();
        player.addNobility(new Nobility(new Tokens(), 4));
        player.addNobility(new Nobility(new Tokens(), 3));

        // when
        int playerPoints = player.getPoints();

        // then
        assertEquals(7, playerPoints);
    }

    @Test
    public void shouldCountPoints() {
        // given
        Player player = new Player();
        player.addCard(new MediumCard(new Tokens(), 4, Green));
        player.addCard(new MediumCard(new Tokens(), 3, Green));
        player.addCard(new ExpensiveCard(new Tokens(), 5, Green));
        player.addNobility(new Nobility(new Tokens(), 3));

        // when
        int points = player.getPoints();

        // then
        assertEquals(15, points);
    }

    @Test
    public void shouldSetTokens() {
        // given
        Player player = new Player();
        Tokens tokens = new Tokens(1, 2, 3, 4, 5);

        // when
        player.setTokens(tokens);

        // then
        assertEquals(tokens, player.getTokens());
    }

    @Test
    public void shouldIncludeReservedCardToPlayerResources() {
        // given
        Player player = new Player(new Tokens(0, 1, 1, 1, 1));
        Card card = new CheapCard(new Tokens(), 0, Green);
        card.setReserved(true);
        player.addCard(card);
        player.addCard(new CheapCard(new Tokens(1, 0, 0, 0, 0), 0, Green));

        // when
        Tokens tokens = player.getTokensIncludingBoughtCards();

        // then
        assertEquals(new Tokens(1, 1, 1, 1, 1), tokens);
    }
}
