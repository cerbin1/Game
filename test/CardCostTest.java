import app.game.card.CardCost;
import app.game.token.Tokens;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(3, 0, 0, 0));
        Tokens tokens = cost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{3, 0, 0, 0, 0}, tokensValue);
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(4, 2, 2, 3, 3));
        Tokens tokens = cost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{0, 0, 2, 2, 0}, tokensValue);
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 0, 0, 1, 4));
        Tokens tokens = cost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{3, 1, 0, 0, 1}, tokensValue);
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 0, 1, 2, 4));
        Tokens tokens = cost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{2, 1, 1, 0, 1}, tokensValue);
    }

    @Test
    public void shouldRandomFiveTypesOfCheapCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 0, 1, 2, 3, 4));
        Tokens tokens = cost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, tokensValue);
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(5, 2, 2, 2, 2, 2));
        Tokens tokens = cost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{0, 0, 5, 0, 0}, tokensValue);
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(6, 0, 0, 0, 2, 2, 2));
        Tokens tokens = cost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{3, 0, 3, 0, 0}, tokensValue);
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 1, 1, 1, 2, 2, 4, 4, 4));
        Tokens tokens = cost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{0, 3, 2, 0, 3}, tokensValue);
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 0, 0, 1, 1, 2, 2, 3, 3));
        Tokens tokens = cost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{2, 2, 2, 2, 0}, tokensValue);
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(8, 0, 1, 2, 3, 3, 3, 3, 4));
        Tokens tokens = cost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{1, 1, 1, 4, 1}, tokensValue);
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(7, 1, 1, 1, 1, 1, 1, 1));
        Tokens tokens = cost.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{0, 7, 0, 0, 0}, tokensValue);
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(10, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3));
        Tokens tokens = cost.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{5, 0, 0, 5, 0}, tokensValue);
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(11, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4));
        Tokens tokens = cost.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 0, 0, 2, 8});
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(12, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4));
        Tokens tokens = cost.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{3, 0, 3, 3, 3}, tokensValue);
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        CardCost cost = new CardCost(new MockProbability(14, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4));
        Tokens tokens = cost.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(new int[]{2, 2, 1, 4, 5}, tokensValue);
    }
}