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
        Card cheapCard1 = factory.createCheapCard();

        // then
        assertEquals(cheapCard1.getCost(), new Tokens(3, 0, 0, 0, 0));
        assertEquals(cheapCard1.getPoints(), 0);
        assertEquals(cheapCard1.getColor(), TokenColor.Green);
    }

    @Test
    public void shouldCreateMediumCards() {
        // given
        Random random = new MockRandom(0, 0, 1, 0);
        CardFactory factory = new CardFactory(random);

        // when
        Card mediumCard1 = factory.createMediumCard();
        Card mediumCard2 = factory.createMediumCard();

        // then
        assertEquals(mediumCard1.getCost(), new Tokens(5, 0, 0, 0, 0));
        assertEquals(mediumCard1.getPoints(), 1);
        assertEquals(mediumCard1.getColor(), TokenColor.Green);
        assertEquals(mediumCard2.getCost(), new Tokens(1, 1, 2, 1, 1));
        assertEquals(mediumCard2.getPoints(), 3);
        assertEquals(mediumCard2.getColor(), TokenColor.Purple);
    }

    @Test
    public void shouldCreateExpensiveCards() {
        // given
        Random random = new MockRandom(0, 0, 1, 0);
        CardFactory builder = new CardFactory(random);

        // when
        Card expensiveCard1 = builder.createExpensiveCard();
        Card expensiveCard2 = builder.createExpensiveCard();

        // then
        assertEquals(expensiveCard1.getCost(), new Tokens(7, 0, 0, 0, 0));
        assertEquals(expensiveCard1.getPoints(), 3);
        assertEquals(expensiveCard1.getColor(), TokenColor.Blue);
        assertEquals(expensiveCard2.getCost(), new Tokens());
        assertEquals(expensiveCard2.getPoints(), 5);
        assertEquals(expensiveCard2.getColor(), TokenColor.Black);
    }
}