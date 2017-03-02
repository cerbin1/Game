import game.TokenColor;
import game.Tokens;
import game.cards.Card;
import game.cards.CardBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardBuilderTest {
    @Test
    public void shouldCreateCheapCards() {
        // given
        MockRandom random = new MockRandom(0, 0, 1, 0);
        CardBuilder builder = new CardBuilder(random);

        // when
        Card cheapCard1 = builder.createCheapCard();
        Card cheapCard2 = builder.createCheapCard();

        // then
        assertEquals(cheapCard1.getCost(), new Tokens(3, 0, 0, 0, 0));
        assertEquals(cheapCard1.getPoints(), 1);
        assertEquals(cheapCard1.getColor(), TokenColor.Green);
        assertEquals(cheapCard2.getCost(), new Tokens(1, 1, 1, 1, 0));
        assertEquals(cheapCard2.getPoints(), 0);
        assertEquals(cheapCard2.getColor(), TokenColor.Red);

    }

    @Test
    public void shouldCreateMediumCards() {
        // given
        MockRandom random = new MockRandom(0, 0, 1, 0);
        CardBuilder builder = new CardBuilder(random);

        // when
        Card mediumCard1 = builder.createMediumCard();
        Card mediumCard2 = builder.createMediumCard();

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
        MockRandom random = new MockRandom(0, 0, 1, 0);
        CardBuilder builder = new CardBuilder(random);

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