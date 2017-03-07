import app.game.card.Card;
import app.game.card.CardFactory;
import app.game.token.TokenColor;
import app.game.token.Tokens;
import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardFactoryTest {
    @Test
    public void shouldCreateCheapCards() {
        // given
        Random random = new MockRandom(0, 0, 1, 0, 0);
        CardFactory factory = new CardFactory(random);

        // when
        Card cheapCard = factory.createCheapCard();

        // then
        assertEquals(new Tokens(3, 0, 0, 0, 0), cheapCard.getCost());
        assertEquals(0, cheapCard.getPoints());
        assertEquals(TokenColor.Green, cheapCard.getColor());
    }

    @Test
    public void shouldCreateMediumCards() {
        // given
        CardFactory factory = new CardFactory(new MockRandom(0, 0, 1));

        // when
        Card mediumCard = factory.createMediumCard();

        // then
        assertEquals(new Tokens(5, 0, 0, 0, 0), mediumCard.getCost());
        assertEquals(1, mediumCard.getPoints());
        assertEquals(TokenColor.Green, mediumCard.getColor());
    }

    @Test
    public void shouldCreateExpensiveCards() {
        // given
        CardFactory builder = new CardFactory(new MockRandom(0, 0, 1));

        // when
        Card expensiveCard = builder.createExpensiveCard();

        // then
        assertEquals(new Tokens(7, 0, 0, 0, 0), expensiveCard.getCost());
        assertEquals(3, expensiveCard.getPoints());
        assertEquals(TokenColor.Blue, expensiveCard.getColor());
    }
}
