package app.model.card;

import app.model.token.TokensAmount;
import app.model.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardCostTest {

    @Test
    public void shouldRandomOneTypeOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(3);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getCheap();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(3)).nextInt(0, 5);
        assertEquals(new TokensAmount(3, 0, 0, 0, 0), tokensAmount);
    }

    @Test
    public void shouldRandomTwoTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(4);
        when(probability.nextInt(0, 5)).thenReturn(2, 2, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getCheap();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(4)).nextInt(0, 5);
        assertEquals(new TokensAmount(0, 0, 2, 2, 0), tokensAmount);
    }

    @Test
    public void shouldRandomThreeTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0, 1, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getCheap();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(5)).nextInt(0, 5);
        assertEquals(new TokensAmount(3, 1, 0, 0, 1), tokensAmount);
    }

    @Test
    public void shouldRandomFourTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 1, 2, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getCheap();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(5)).nextInt(0, 5);
        assertEquals(new TokensAmount(2, 1, 1, 0, 1), tokensAmount);
    }

    @Test
    public void shouldRandomFiveTypesOfCheapCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5);
        when(probability.nextInt(0, 5)).thenReturn(0, 1, 2, 3, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getCheap();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(5)).nextInt(0, 5);
        assertEquals(new TokensAmount(1, 1, 1, 1, 1), tokensAmount);
    }

    @Test
    public void shouldRandomOneTypeOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(5);
        when(probability.nextInt(0, 5)).thenReturn(2, 2, 2, 2, 2);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getMedium();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(5)).nextInt(0, 5);
        assertEquals(new TokensAmount(0, 0, 5, 0, 0), tokensAmount);
    }

    @Test
    public void shouldRandomTwoTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(6);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0, 2, 2, 2);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getMedium();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(6)).nextInt(0, 5);
        assertEquals(new TokensAmount(3, 0, 3, 0, 0), tokensAmount);
    }

    @Test
    public void shouldRandomThreeTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(8);
        when(probability.nextInt(0, 5)).thenReturn(1, 1, 1, 2, 2, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getMedium();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(8)).nextInt(0, 5);
        assertEquals(new TokensAmount(0, 3, 2, 0, 3), tokensAmount);
    }

    @Test
    public void shouldRandomFourTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(8);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 1, 1, 2, 2, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getMedium();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(8)).nextInt(0, 5);
        assertEquals(new TokensAmount(2, 2, 2, 2, 0), tokensAmount);
    }

    @Test
    public void shouldRandomFiveTypesOfMediumCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(8);
        when(probability.nextInt(0, 5)).thenReturn(0, 1, 2, 3, 3, 3, 3, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getMedium();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(8)).nextInt(0, 5);
        assertEquals(new TokensAmount(1, 1, 1, 4, 1), tokensAmount);
    }

    @Test
    public void shouldRandomOneTypeOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(7);
        when(probability.nextInt(0, 5)).thenReturn(1, 1, 1, 1, 1, 1, 1);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getExpensive();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(7)).nextInt(0, 5);
        assertEquals(new TokensAmount(0, 7, 0, 0, 0), tokensAmount);
    }

    @Test
    public void shouldRandomTwoTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(10);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0, 0, 0, 3, 3, 3, 3, 3);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getExpensive();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(10)).nextInt(0, 5);
        assertEquals(new TokensAmount(5, 0, 0, 5, 0), tokensAmount);
    }

    @Test
    public void shouldRandomThreeTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(11);
        when(probability.nextInt(0, 5)).thenReturn(0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getExpensive();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(11)).nextInt(0, 5);
        assertEquals(new TokensAmount(1, 0, 0, 2, 8), tokensAmount);
    }

    @Test
    public void shouldRandomFourTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(12);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getExpensive();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(12)).nextInt(0, 5);
        assertEquals(new TokensAmount(3, 0, 3, 3, 3), tokensAmount);
    }

    @Test
    public void shouldRandomFiveTypesOfExpensiveCostCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(14);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4);
        CardCost cost = new CardCost(probability);

        // when
        TokensAmount tokensAmount = cost.getExpensive();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(14)).nextInt(0, 5);
        assertEquals(new TokensAmount(2, 2, 1, 4, 5), tokensAmount);
    }
}
