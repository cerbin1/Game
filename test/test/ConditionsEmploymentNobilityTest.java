package test;

import game.ConditionsEmploymentNobility;
import game.Tokens;
import org.junit.Assert;
import org.junit.Test;

public class ConditionsEmploymentNobilityTest {
    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        MockRandom mockRandom = new MockRandom(0, 1, 3, 4);
        ConditionsEmploymentNobility conditionsEmploymentNobility = new ConditionsEmploymentNobility(mockRandom);
        Tokens tokens = conditionsEmploymentNobility.getRandomCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(tokensValue, new int[]{0, 3, 0, 3, 3});
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        MockRandom mockRandom = new MockRandom(1, 2, 4);
        ConditionsEmploymentNobility conditionsEmploymentNobility = new ConditionsEmploymentNobility(mockRandom);
        Tokens tokens = conditionsEmploymentNobility.getRandomCost();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getWhite(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(tokensValue, new int[]{0, 0, 4, 0, 4});
    }
}