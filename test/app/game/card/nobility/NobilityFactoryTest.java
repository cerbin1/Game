package app.game.card.nobility;

import app.game.token.Tokens;
import org.junit.Assert;
import org.junit.Test;
import testUtils.MockProbability;

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
        Nobility nobility = factory.create();

        // when
        Tokens tokens = nobility.getCondition();

        // then
        Assert.assertEquals(new Tokens(0, 3, 3, 3, 0), tokens);
    }

    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        NobilityFactory factory = new NobilityFactory(new MockProbability(1, 3, 4, 4));
        Nobility nobility = factory.create();

        // when
        Tokens tokens = nobility.getCondition();

        // then
        Assert.assertEquals(new Tokens(0, 0, 0, 4, 4), tokens);
    }
}