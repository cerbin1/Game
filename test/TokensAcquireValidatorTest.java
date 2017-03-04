import app.game.token.Tokens;
import app.game.token.TokensAcquireValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokensAcquireValidatorTest {
    @Test
    public void shouldAcquireTwoTokensSameColor() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(7, 5));

        // when
        boolean canAcquire = validator.canAcquire(new Tokens(0, 2, 0, 0, 0));

        // then
        assertTrue(canAcquire);
    }

    @Test
    public void shouldAcquireThreeTokensDifferentColor() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(7, 5));

        // when
        boolean canAcquire = validator.canAcquire(new Tokens(0, 1, 1, 1, 0));

        // then
        assertTrue(canAcquire);
    }

    @Test
    public void shouldNotAcquireTokensOnInvalidAmount() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(7, 5));

        // when
        boolean result1 = validator.canAcquire(new Tokens(0, 0, 0, 0, 0));
        boolean result2 = validator.canAcquire(new Tokens(1, 0, 0, 0, 0));
        boolean result3 = validator.canAcquire(new Tokens(1, 0, 0, 1, 0));
        boolean result4 = validator.canAcquire(new Tokens(1, 1, 1, 1, 1));
        boolean result5 = validator.canAcquire(new Tokens(2, 0, 2, 0, 0));
        boolean result6 = validator.canAcquire(new Tokens(3, 0, 0, 0, 0));
        boolean result7 = validator.canAcquire(new Tokens(1, 0, 0, 2, 0));
        boolean result8 = validator.canAcquire(new Tokens(10, 0, 0, 0, 0));

        // then
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
        assertFalse(result5);
        assertFalse(result6);
        assertFalse(result7);
        assertFalse(result8);
    }

    @Test
    public void shouldNotAcquireTokensWhenAfterAcquireNoTokens() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(0, 0, 0, 2, 0));

        // when
        boolean result = validator.canAcquire(new Tokens(0, 0, 0, 2, 0));

        // then
        assertFalse(result);
    }

    @Test
    public void shouldNotAcquireTokensWhenNotEnoughTokens() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(1, 1, 0, 1, 0));

        // when
        boolean result1 = validator.canAcquire(new Tokens(1, 1, 1, 0, 0));
        boolean result2 = validator.canAcquire(new Tokens(0, 0, 0, 2, 0));

        // then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void shouldNotAcquireVersatile() {
        // given
        TokensAcquireValidator validator = new TokensAcquireValidator(new Tokens(7, 5));

        // when
        boolean result = validator.canAcquire(new Tokens(1, 1));

        // then
        assertFalse(result);
    }
}