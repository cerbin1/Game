package test;

import game.CardCost;
import game.Tokens;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(0, 0));
        Tokens tokens = cardCost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 0, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(2, 2, 3));
        Tokens tokens = cardCost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 2, 2, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(4, 0, 1, 4));
        Tokens tokens = cardCost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 1, 0, 0, 1});
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(6, 0, 1, 2, 4));
        Tokens tokens = cardCost.getCheap();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 1, 1, 0, 1});
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(0, 2));
        Tokens tokens = cardCost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 5, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(2, 1, 3));
        Tokens tokens = cardCost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 4, 0, 7, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(5, 0, 2, 1));
        Tokens tokens = cardCost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 3, 2, 0, 0});
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(9, 0, 2, 3, 4));
        Tokens tokens = cardCost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 0, 2, 4, 1});
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(12, 0, 1, 2, 3, 4));
        Tokens tokens = cardCost.getMedium();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{1, 1, 2, 1, 1});
    }
}