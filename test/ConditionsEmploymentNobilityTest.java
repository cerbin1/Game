import app.game.token.Tokens;
import app.game.card.nobility.ConditionsEmploymentNobility;
import app.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class ConditionsEmploymentNobilityTest {
    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        Random random = new MockRandom(0, 1, 2, 3);
        ConditionsEmploymentNobility conditionsEmploymentNobility = new ConditionsEmploymentNobility(random);
        Tokens tokens = conditionsEmploymentNobility.getConditions();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(tokensValue, new int[]{0, 3, 3, 3, 0});
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        Random random = new MockRandom(1, 3, 4);
        ConditionsEmploymentNobility conditionsEmploymentNobility = new ConditionsEmploymentNobility(random);
        Tokens tokens = conditionsEmploymentNobility.getConditions();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(tokensValue, new int[]{0, 0, 0, 4, 4});
    }
}