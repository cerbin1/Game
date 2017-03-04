import app.game.card.CardCostGenerator;
import app.game.token.Tokens;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CardCostGeneratorTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(3, 0, 0, 0));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 0, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(4, 2, 2, 3, 3));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 2, 2, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 0, 0, 0, 1, 4));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 1, 0, 0, 1});
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 0, 0, 1, 2, 4));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 1, 1, 0, 1});
    }

    @Test
    public void shouldRandomFiveTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 0, 1, 2, 3, 4));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 1, 1, 1, 1});
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 2, 2, 2, 2, 2));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 5, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(6, 0, 0, 0, 2, 2, 2));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 3, 0, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(8, 1, 1, 1, 2, 2, 4, 4, 4));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 3, 2, 0, 3});
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(8, 0, 0, 1, 1, 2, 2, 3, 3));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 2, 2, 2, 0});
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(8, 0, 1, 2, 3, 3, 3, 3, 4));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 1, 1, 4, 1});
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(7, 1, 1, 1, 1, 1, 1, 1));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 7, 0, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(10, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{5, 0, 0, 5, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(11, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 0, 0, 2, 8});
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(12, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 3, 3, 3});
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(14, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 2, 1, 4, 5});
    }
}