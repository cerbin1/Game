package app.game.token;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourcesTest {
    @Test
    public void shouldCouldBuy() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 0, 1);
        Resources resources = new Resources(stationary, temporary, 2);

        // when
        boolean canBuy = resources.canBuy(new Tokens(4, 2, 4, 0, 1));

        // then
        assertTrue(canBuy);
    }

    @Test
    public void shouldNotCouldBuy() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 0, 1);
        Resources resources = new Resources(stationary, temporary, 1);

        // when
        boolean canBuy = resources.canBuy(new Tokens(4, 2, 4, 0, 1));

        // then
        assertFalse(canBuy);
    }

    @Test
    public void shouldCalculateChange() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 1, 0);
        Resources resources = new Resources(stationary, temporary, 0);

        // when
        Tokens tokens = resources.calculateChange(new Tokens(2, 2, 2, 1, 1));

        // then
        assertEquals(new Tokens(1, -1, 2, 0, -1), tokens);
    }

    @Test
    public void shouldCompensateChangeWithVersatile() {
        // given
        Tokens stationary = new Tokens(1, 0, 2, 0, 0);
        Tokens temporary = new Tokens(2, 1, 2, 1, 0);
        Resources resources = new Resources(stationary, temporary, 3);

        // when
        Tokens tokens = resources.calculateChange(new Tokens(2, 2, 2, 1, 1));

        // then
        assertEquals(new Tokens(1, 0, 2, 0, 0, 1), tokens);
    }
}