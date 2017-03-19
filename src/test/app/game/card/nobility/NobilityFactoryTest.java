package app.game.card.nobility;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NobilityFactoryTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 1)).thenReturn(0);
        when(probability.nextInt(0, 4)).thenReturn(0, 1, 2);
        when(probability.nextInt(3, 4)).thenReturn(3);
        NobilityFactory factory = new NobilityFactory(probability);

        // when
        Nobility nobility = factory.create();

        // then
        verify(probability, times(1)).nextInt(3, 4);
        assertEquals(3, nobility.getPoints());
    }

    @Test
    public void shouldRandomThreeCostTypes() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 1)).thenReturn(0);
        when(probability.nextInt(0, 4)).thenReturn(1, 2, 3);
        when(probability.nextInt(3, 4)).thenReturn(3);
        NobilityFactory factory = new NobilityFactory(probability);

        // when
        Nobility nobility = factory.create();

        // then
        verify(probability, times(1)).nextInt(0, 1);
        verify(probability, times(3)).nextInt(0, 4);
        assertEquals(new Tokens(0, 3, 3, 3, 0), nobility.getCost());
    }

    @Test
    public void shouldRandomTwoCostTypes() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 1)).thenReturn(1);
        when(probability.nextInt(0, 4)).thenReturn(3, 4);
        when(probability.nextInt(3, 4)).thenReturn(4);
        NobilityFactory factory = new NobilityFactory(probability);

        // when
        Nobility nobility = factory.create();

        // then
        verify(probability, times(1)).nextInt(0, 1);
        verify(probability, times(2)).nextInt(0, 4);
        assertEquals(new Tokens(0, 0, 0, 4, 4), nobility.getCost());
    }
}