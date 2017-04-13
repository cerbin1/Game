package app.model.token;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourcesTest {
    @Test
    public void shouldCouldBuy() {
        // given
        Tokens stationary = new Tokens(1, 0);
        Tokens temporary = new Tokens(1, 5);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(3, 0));

        // then
        assertTrue(result.canBuy());
    }

    @Test
    public void shouldNotCouldBuy() {
        // given
        Tokens stationary = new Tokens(1, 0);
        Tokens temporary = new Tokens(1, 4);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(3, 0));

        // then
        assertFalse(result.canBuy());
    }

    @Test
    public void shouldCalculateOverflowingChange() {
        // given
        Tokens stationary = new Tokens(13, 0);
        Tokens temporary = new Tokens(4, 0);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(2, 0));

        // then
        assertEquals(new Tokens(4, 0), result.getRemaining());
    }

    @Test
    public void shouldNotLoseVersatile() {
        // given
        Tokens stationary = new Tokens(1, 0, 0, 0, 0, 0);
        Tokens temporary = new Tokens(4, 0, 0, 0, 0, 1);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(5, 0, 0, 0, 0, 0));

        // then
        assertEquals(new Tokens(0, 1), result.getRemaining());
    }

    @Test
    public void shouldCompensateChangeWithVersatile() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 1, 0, 3);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(2, 2, 2, 1, 1));

        // then
        assertEquals(new Tokens(1, 0, 2, 0, 0, 1), result.getRemaining());
    }

    @Test
    public void shouldGetSpentTokens() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 1, 0, 1);
        Resources resources = new Resources(stationary, temporary);

        // when
        BuyingResult result = resources.buy(new Tokens(2, 0, 3, 2, 0));

        // then
        assertEquals(new Tokens(1, 0, 1, 1, 0, 1), result.getSpent());
    }
}
