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
        assertEquals(cheapCard.getCost(), new Tokens(3, 0, 0, 0, 0));
        assertEquals(cheapCard.getPoints(), 0);
        assertEquals(cheapCard.getColor(), TokenColor.Green);
    }

    @Test
    public void shouldCreateMediumCards() {
        // given
        CardFactory factory = new CardFactory(new MockRandom(0, 0, 1));

        // when
        Card mediumCard = factory.createMediumCard();

        // then
        assertEquals(mediumCard.getCost(), new Tokens(5, 0, 0, 0, 0));
        assertEquals(mediumCard.getPoints(), 1);
        assertEquals(mediumCard.getColor(), TokenColor.Green);
    }

    @Test
    public void shouldCreateExpensiveCards() {
        // given
        CardFactory builder = new CardFactory(new MockRandom(0, 0, 1));

        // when
        Card expensiveCard = builder.createExpensiveCard();

        // then
        assertEquals(expensiveCard.getCost(), new Tokens(7, 0, 0, 0, 0));
        assertEquals(expensiveCard.getPoints(), 3);
        assertEquals(expensiveCard.getColor(), TokenColor.Blue);
    }
}
