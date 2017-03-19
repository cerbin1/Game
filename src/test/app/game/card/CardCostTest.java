package app.game.card;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(3, 0, 0, 0);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getCheap();

        // then
        verify(probability, times(4)).nextInt(3, 5);
        assertEquals(new Tokens(3, 0, 0, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(4, 2, 2, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getCheap();

        // then
        verify(probability, times(5)).nextInt(3, 5);
        assertEquals(new Tokens(0, 0, 2, 2, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5, 0, 0, 0, 1, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getCheap();

        // then
        verify(probability, times(6)).nextInt(3, 5);
        assertEquals(new Tokens(3, 1, 0, 0, 1), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5, 0, 0, 1, 2, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getCheap();

        // then
        verify(probability, times(6)).nextInt(3, 5);
        assertEquals(new Tokens(2, 1, 1, 0, 1), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5, 0, 1, 2, 3, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getCheap();

        // then
        verify(probability, times(6)).nextInt(3, 5);
        assertEquals(new Tokens(1, 1, 1, 1, 1), tokens);
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5, 2, 2, 2, 2, 2);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getMedium();

        // then
        verify(probability, times(6)).nextInt(3, 5);
        assertEquals(new Tokens(0, 0, 5, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(6, 0, 0, 0, 2, 2, 2);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getMedium();

        // then
        verify(probability, times(7)).nextInt(3, 5);
        assertEquals(new Tokens(3, 0, 3, 0, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(8, 1, 1, 1, 2, 2, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getMedium();

        // then
        verify(probability, times(9)).nextInt(3, 5);
        assertEquals(new Tokens(0, 3, 2, 0, 3), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(8, 0, 0, 1, 1, 2, 2, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getMedium();

        // then
        verify(probability, times(9)).nextInt(3, 5);
        assertEquals(new Tokens(2, 2, 2, 2, 0), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(8, 0, 1, 2, 3, 3, 3, 3, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getMedium();

        // then
        verify(probability, times(9)).nextInt(3, 5);
        assertEquals(new Tokens(1, 1, 1, 4, 1), tokens);
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(7, 1, 1, 1, 1, 1, 1, 1);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getExpensive();

        // then
        verify(probability, times(8)).nextInt(3, 5);
        assertEquals(new Tokens(0, 7, 0, 0, 0), tokens);
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(10, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getExpensive();

        // then
        verify(probability, times(11)).nextInt(3, 5);
        assertEquals(new Tokens(5, 0, 0, 5, 0), tokens);
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(11, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getExpensive();

        // then
        verify(probability, times(12)).nextInt(3, 5);
        assertEquals(new Tokens(1, 0, 0, 2, 8), tokens);
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(12, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getExpensive();

        // then
        verify(probability, times(13)).nextInt(3, 5);
        assertEquals(new Tokens(3, 0, 3, 3, 3), tokens);
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(14, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        Tokens tokens = cost.getExpensive();

        // then
        verify(probability, times(15)).nextInt(3, 5);
        assertEquals(new Tokens(2, 2, 1, 4, 5), tokens);
    }
}
