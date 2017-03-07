import app.game.card.nobility.Nobility;
import app.game.card.nobility.NobilityFactory;
import app.game.token.Tokens;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NobilityFactoryTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockProbability(0, 0, 1, 2, 3));

        // when
        Nobility nobility = factory.create();

        // then
        assertEquals(3, nobility.getPoints());
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockProbability(0, 1, 2, 3, 3));

        // when
        Tokens tokens = factory.create().getCondition();

        // then
        Assert.assertArrayEquals(new int[]{0, 3, 3, 3, 0}, new int[]{tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()});
    }

    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockProbability(1, 3, 4, 4));

        // when
        Tokens tokens = factory.create().getCondition();

        // then
        Assert.assertArrayEquals(new int[]{0, 0, 0, 4, 4}, new int[]{tokens.getGreen(), tokens.getPurple(), tokens.getBlue(), tokens.getBlack(), tokens.getRed()});
    }
}