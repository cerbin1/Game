import app.game.Player;
import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.card.ExpensiveCard;
import app.game.card.MediumCard;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import org.junit.Test;

import static app.game.token.TokenColor.Green;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void shouldCreatePlayerWithDefaultPoints() {
        // given
        Player player1 = new Player();
        Player player2 = new Player(new Tokens(1, 1, 1, 1, 1));
        Player player3 = new Player(new Tokens(0, 4, 0, 4, 0));
        Player player4 = new Player(new Tokens(5, 5, 5, 5, 5));

        // when
        int[] tokensValue1 = {player1.getTokens().getGreen(), player1.getTokens().getPurple(), player1.getTokens().getBlue(), player1.getTokens().getBlack(), player1.getTokens().getRed()};
        int[] tokensValue2 = {player2.getTokens().getGreen(), player2.getTokens().getPurple(), player2.getTokens().getBlue(), player2.getTokens().getBlack(), player2.getTokens().getRed()};
        int[] tokensValue3 = {player3.getTokens().getGreen(), player3.getTokens().getPurple(), player3.getTokens().getBlue(), player3.getTokens().getBlack(), player3.getTokens().getRed()};
        int[] tokensValue4 = {player4.getTokens().getGreen(), player4.getTokens().getPurple(), player4.getTokens().getBlue(), player4.getTokens().getBlack(), player4.getTokens().getRed()};

        // then
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, tokensValue1);
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, tokensValue2);
        assertArrayEquals(new int[]{0, 4, 0, 4, 0}, tokensValue3);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, tokensValue4);
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
}
