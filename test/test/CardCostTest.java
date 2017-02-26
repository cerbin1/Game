package test;

import game.CardCost;
import game.Tokens;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CardCostTest {
    private CardCost cardCost = new CardCost(new MockRandom(0, 0));

    @Test
    public void shouldRandomFirstTypeOfCheapCardCost() {
        // given
        Tokens tokens = cardCost.getRandomCheapCardCost();

        // when
        List<Integer> tokensValues = Arrays.asList(tokens.getBlack(), tokens.getBlue(), tokens.getGreen(), tokens.getRed(), tokens.getWhite());

        // then
        assertTrue(tokensValues.contains(3));
    }
}