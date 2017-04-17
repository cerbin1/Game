package app.model.token;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static app.model.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class TokensAmountTest {
    @Test
    public void shouldGetTokens() {
        // given
        TokensAmount tokensAmount = new TokensAmount(3, 2);

        // when
        int green = tokensAmount.get(Green);
        int purple = tokensAmount.get(Purple);
        int blue = tokensAmount.get(Blue);
        int black = tokensAmount.get(Black);
        int red = tokensAmount.get(Red);
        int versatile = tokensAmount.getVersatile();

        // then
        assertEquals(3, green);
        assertEquals(3, purple);
        assertEquals(3, blue);
        assertEquals(3, black);
        assertEquals(3, red);
        assertEquals(2, versatile);
    }

    @Test
    public void shouldGetSeparateTokens() {
        // given
        TokensAmount tokensAmount = new TokensAmount(0, 4, 2, 12, 4);

        // when
        int green = tokensAmount.get(Green);
        int purple = tokensAmount.get(Purple);
        int blue = tokensAmount.get(Blue);
        int black = tokensAmount.get(Black);
        int red = tokensAmount.get(Red);

        // then
        assertEquals(0, green);
        assertEquals(4, purple);
        assertEquals(2, blue);
        assertEquals(12, black);
        assertEquals(4, red);
    }

    @Test
    public void shouldGetTokensAsMap() {
        // given
        TokensAmount tokensAmount = new TokensAmount(3, 2);

        // when
        Map<TokenColor, Integer> map = tokensAmount.asMap();

        // then
        assertEquals(new EnumMap<TokenColor, Integer>(TokenColor.class) {{
            put(Green, 3);
            put(Purple, 3);
            put(Blue, 3);
            put(Black, 3);
            put(Red, 3);
        }}, map);
    }

    @Test
    public void shouldCopyTokens() {
        // given
        TokensAmount original = new TokensAmount(1, 2, 3, 4, 5);

        // when
        TokensAmount copy = new TokensAmount(original);

        // then
        assertEquals(copy, original);
    }

    @Test
    public void shouldAddTokens() {
        // given
        TokensAmount first = new TokensAmount(1, 2, 3, 4, 5, 6);
        TokensAmount second = new TokensAmount(0, 4, 4, 1, 5, 6);

        // when
        TokensAmount result = first.add(second);

        // then
        assertEquals(new TokensAmount(1, 6, 7, 5, 10, 12), result);
    }

    @Test
    public void shouldSubtractTokens() {
        // given
        TokensAmount first = new TokensAmount(1, 2, 3, 4, 5, 6);
        TokensAmount second = new TokensAmount(0, 4, 4, 1, 5, 5);

        // when
        TokensAmount result = first.subtract(second);

        // then
        assertEquals(new TokensAmount(1, -2, -1, 3, 0, 1), result);
    }

    @Test
    public void shouldReturnAsCost() {
        // given
        TokensAmount tokensAmount = new TokensAmount(1, 2, 3, 4, 5, 6);

        // when
        TokensAmount cost = tokensAmount.asCost();

        // then
        assertEquals(new TokensAmount(1, 2, 3, 4, 5, 0), cost);
    }

    @Test
    public void shouldGetMapAndVersatile() {
        // given
        Map<TokenColor, Integer> map = new EnumMap<>(TokenColor.class);
        map.put(Green, 2);
        map.put(Purple, 2);
        map.put(Blue, 2);
        map.put(Black, 2);
        map.put(Red, 2);

        // when
        TokensAmount tokensAmount = new TokensAmount(map, 2);

        // then
        assertEquals(new TokensAmount(2, 2, 2, 2, 2, 2), tokensAmount);
    }

    @Test
    public void shouldRemoveDebts() {
        // given
        TokensAmount debts = new TokensAmount(1, -1, 0, 4, -5, -3);

        // when
        TokensAmount tokensAmount = TokensAmount.Operations.removeDebts(debts);

        // then
        assertEquals(new TokensAmount(1, 0, 0, 4, 0, -3), tokensAmount);
    }
}
