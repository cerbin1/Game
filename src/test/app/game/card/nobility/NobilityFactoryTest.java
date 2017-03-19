package app.game.card.nobility;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NobilityFactoryTest {
    @Test
    public void shouldCreateNobility() {
        // given
        Probability probability = mock(Probability.class);
        NobilityFactory factory = new NobilityFactory(probability);

        when(probability.nextInt(0, 1)).thenReturn(0);
        when(probability.nextInt(0, 4)).thenReturn(1, 2, 3);
        when(probability.nextInt(3, 4)).thenReturn(4);

        // when
        Nobility nobility = factory.create();

        // then
        assertEquals(new Tokens(0, 3, 3, 3, 0), nobility.getCost());
        assertEquals(4, nobility.getPoints());

        verify(probability, times(1)).nextInt(0, 1);
        verify(probability, times(3)).nextInt(0, 4);
        verify(probability, times(1)).nextInt(3, 4);
    }
}
