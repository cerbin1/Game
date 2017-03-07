import app.game.card.CardCost;
import app.game.token.Tokens;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(3, 0, 0, 0));

        // when
        Tokens tokens = cost.getCheap();

        // then
        assertEquals(new Tokens(3, 0, 0, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(4, 2, 2, 3, 3));

        // when
        Tokens tokens = cost.getCheap();

        // then
        assertEquals(new Tokens(0, 0, 2, 2, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 0, 0, 1, 4));

        // when
        Tokens tokens = cost.getCheap();

        // then
        assertEquals(new Tokens(3, 1, 0, 0, 1), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 0, 1, 2, 4));

        // when
        Tokens tokens = cost.getCheap();

        // then
        assertEquals(new Tokens(2, 1, 1, 0, 1), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 1, 2, 3, 4));

        // when
        Tokens tokens = cost.getCheap();

        // then
        assertEquals(new Tokens(1, 1, 1, 1, 1), tokens);
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 2, 2, 2, 2, 2));

        // when
        Tokens tokens = cost.getMedium();

        // then
        assertEquals(new Tokens(0, 0, 5, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(6, 0, 0, 0, 2, 2, 2));

        // when
        Tokens tokens = cost.getMedium();

        // then
        assertEquals(new Tokens(3, 0, 3, 0, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 1, 1, 1, 2, 2, 4, 4, 4));

        // when
        Tokens tokens = cost.getMedium();

        // then
        assertEquals(new Tokens(0, 3, 2, 0, 3), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 0, 0, 1, 1, 2, 2, 3, 3));

        // when
        Tokens tokens = cost.getMedium();

        // then
        assertEquals(new Tokens(2, 2, 2, 2, 0), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 0, 1, 2, 3, 3, 3, 3, 4));

        // when
        Tokens tokens = cost.getMedium();

        // then
        assertEquals(new Tokens(1, 1, 1, 4, 1), tokens);
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(7, 1, 1, 1, 1, 1, 1, 1));

        // when
        Tokens tokens = cost.getExpensive();

        // then
        assertEquals(new Tokens(0, 7, 0, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(10, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3));

        // when
        Tokens tokens = cost.getExpensive();

        // then
        assertEquals(new Tokens(5, 0, 0, 5, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(11, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4));

        // when
        Tokens tokens = cost.getExpensive();

        // then
        assertEquals(new Tokens(1, 0, 0, 2, 8), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(12, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4));

        // when
        Tokens tokens = cost.getExpensive();

        // then
        assertEquals(new Tokens(3, 0, 3, 3, 3), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(14, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4));

        // when
        Tokens tokens = cost.getExpensive();

        // then
        assertEquals(new Tokens(2, 2, 1, 4, 5), tokens);
    }
}