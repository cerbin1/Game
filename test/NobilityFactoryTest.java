import app.game.card.nobility.ConditionsEmploymentNobility;
import app.game.card.nobility.Nobility;
import app.game.card.nobility.NobilityFactory;
import app.game.token.Tokens;
import app.util.Random;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NobilityFactoryTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockRandom(0, 0, 1, 2, 3));

        // when
        Nobility nobility = factory.create();

        // then
        assertEquals(3, nobility.getPoints());
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockRandom(0, 1, 2, 3, 3));
        Nobility nobility = factory.create();
        Tokens tokens = nobility.getCondition();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(new int[]{0, 3, 3, 3, 0}, tokensValue);
    }

    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockRandom(1, 3, 4, 4));
        Nobility nobility = factory.create();
        Tokens tokens = nobility.getCondition();

        // when
        int[] tokensValue = {tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()};

        // then
        Assert.assertArrayEquals(new int[]{0, 0, 0, 4, 4}, tokensValue);
    }
}