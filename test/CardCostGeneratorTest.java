import app.MockRandom;
import app.game.Tokens;
import app.game.card.CardCostGenerator;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CardCostGeneratorTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(0, 0));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 0, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(2, 2, 3));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 2, 2, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(4, 0, 1, 4));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 1, 0, 0, 1});
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(6, 0, 1, 2, 4));
        Tokens tokens = cardCostGenerator.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 1, 1, 0, 1});
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(0, 2));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 5, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(2, 1, 3));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 4, 0, 7, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 0, 2, 1));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 3, 2, 0, 0});
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(9, 0, 2, 3, 4));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 0, 2, 4, 1});
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(12, 0, 1, 2, 3, 4));
        Tokens tokens = cardCostGenerator.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 1, 2, 1, 1});
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(0, 3));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 0, 7, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(2, 0, 3));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 0, 4, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(5, 0, 1, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 3, 0, 0, 3});
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(10, 0, 1, 3, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 4, 0, 6, 1});
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        CardCostGenerator cardCostGenerator = new CardCostGenerator(new MockRandom(13, 0, 1, 2, 3, 4));
        Tokens tokens = cardCostGenerator.getExpensive();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 1, 5, 2, 1});
    }
}