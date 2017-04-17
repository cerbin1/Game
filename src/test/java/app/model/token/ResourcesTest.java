package app.model.token;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourcesTest {
    @Test
    public void shouldCouldBuy() {
        // given
        TokensAmount stationary = new TokensAmount(1, 0);
        TokensAmount temporary = new TokensAmount(1, 5);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(3, 0));

        // then
        assertTrue(result.canBuy());
    }

    @Test
    public void shouldNotCouldBuy() {
        // given
        TokensAmount stationary = new TokensAmount(1, 0);
        TokensAmount temporary = new TokensAmount(1, 4);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(3, 0));

        // then
        assertFalse(result.canBuy());
    }

    @Test
    public void shouldCalculateOverflowingChange() {
        // given
        TokensAmount stationary = new TokensAmount(13, 0);
        TokensAmount temporary = new TokensAmount(4, 0);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(2, 0));

        // then
        assertEquals(new TokensAmount(4, 0), result.getRemaining());
    }

    @Test
    public void shouldNotLoseVersatile() {
        // given
        TokensAmount stationary = new TokensAmount(1, 0, 0, 0, 0, 0);
        TokensAmount temporary = new TokensAmount(4, 0, 0, 0, 0, 1);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(5, 0, 0, 0, 0, 0));

        // then
        assertEquals(new TokensAmount(0, 1), result.getRemaining());
    }

    @Test
    public void shouldCompensateChangeWithVersatile() {
        // given
        TokensAmount stationary = new TokensAmount(1, 0, 2, 0, 0);
        TokensAmount temporary = new TokensAmount(2, 1, 2, 1, 0, 3);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(2, 2, 2, 1, 1));

        // then
        assertEquals(new TokensAmount(1, 0, 2, 0, 0, 1), result.getRemaining());
    }

    @Test
    public void shouldGetSpentTokens() {
        // given
        TokensAmount stationary = new TokensAmount(1, 0, 2, 0, 0);
        TokensAmount temporary = new TokensAmount(2, 1, 2, 1, 0, 1);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new TokensAmount(2, 0, 3, 2, 0));

        // then
        assertEquals(new TokensAmount(1, 0, 1, 1, 0, 1), result.getSpent());
    }
}
