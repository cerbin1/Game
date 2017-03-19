package app.game.card.nobility;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NobilityFactoryTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 4)).thenReturn(0, 0, 1, 2, 3);
        NobilityFactory factory = new NobilityFactory(probability);

        // when
        Nobility nobility = factory.create();

        // then
        assertEquals(3, nobility.getPoints());
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 4)).thenReturn(0, 1, 2, 3, 3);
        NobilityFactory factory = new NobilityFactory(probability);
        Nobility nobility = factory.create();

        // when
        Tokens tokens = nobility.getCost();

        // then
        assertEquals(new Tokens(0, 3, 3, 3, 0), tokens);
    }

    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 4)).thenReturn(1, 3, 4, 4);
        NobilityFactory factory = new NobilityFactory(probability);
        Nobility nobility = factory.create();

        // when
        Tokens tokens = nobility.getCost();

        // then
        assertEquals(new Tokens(0, 0, 0, 4, 4), tokens);
    }
}