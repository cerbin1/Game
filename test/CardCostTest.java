import game.CardCost;
import game.Tokens;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(0, 0));
        Tokens tokens = cardCost.getRandomCheapCardCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 0, 0, 0, 0});
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(2, 2, 3));
        Tokens tokens = cardCost.getRandomCheapCardCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{0, 0, 2, 2, 0});
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(4, 0, 1, 4));
        Tokens tokens = cardCost.getRandomCheapCardCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{3, 1, 0, 0, 1});
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        CardCost cardCost = new CardCost(new MockRandom(6, 0, 1, 2, 4));
        Tokens tokens = cardCost.getRandomCheapCardCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        assertArrayEquals(tokensValue, new int[]{2, 1, 1, 0, 1});
    }
}