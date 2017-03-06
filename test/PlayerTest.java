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
    public void shouldAddTokens() {
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
        assertArrayEquals(tokensValue1, new int[]{0, 0, 0, 0, 0});
        assertArrayEquals(tokensValue2, new int[]{1, 1, 1, 1, 1});
        assertArrayEquals(tokensValue3, new int[]{0, 4, 0, 4, 0});
        assertArrayEquals(tokensValue4, new int[]{5, 5, 5, 5, 5});
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
        int points = player.countPoints();

        // then
        assertEquals(points, 8);
    }

    @Test
    public void shouldCountPointsFromUnreservedCards() {
        // given
        Player player = new Player();
        Card reservedCard = new CheapCard(new Tokens(), 1, Green);
        reservedCard.setReserved(true);
        player.addCard(reservedCard);
        player.addCard(new CheapCard(new Tokens(), 0, Green));
        player.addCard(new CheapCard(new Tokens(), 1, Green));
        player.addCard(new MediumCard(new Tokens(), 2, Green));
        player.addCard(new ExpensiveCard(new Tokens(), 1, Green));

        // when
        int points = player.countPoints();

        // then
        assertEquals(points, 4);
    }

    @Test
    public void shouldCountPointsFromNobilities() {
        // given
        Player player = new Player();
        player.addNobility(new Nobility(new Tokens(), 4));
        player.addNobility(new Nobility(new Tokens(), 3));

        // when
        int playerPoints = player.countPoints();

        // then
        assertEquals(playerPoints, 7);
    }

    @Test
    public void shouldCountPoints() {
        // given
        Player player = new Player();
        Card reservedCard = new MediumCard(new Tokens(), 4, Green);
        reservedCard.setReserved(true);
        player.addCard(reservedCard);
        player.addCard(new MediumCard(new Tokens(), 3, Green));
        player.addCard(new ExpensiveCard(new Tokens(), 5, Green));
        player.addNobility(new Nobility(new Tokens(), 3));

        // when
        int points = player.countPoints();

        // then
        assertEquals(points, 15);
    }

    @Test
    public void shouldSetTokens() {
        // given
        Player player = new Player();

        // when
        player.setTokens(new Tokens(1, 2, 3, 4, 5));

        // then
        assertEquals(player.getTokens().getGreen(), 1);
        assertEquals(player.getTokens().getPurple(), 2);
        assertEquals(player.getTokens().getBlue(), 3);
        assertEquals(player.getTokens().getBlack(), 4);
        assertEquals(player.getTokens().getRed(), 5);
    }
}