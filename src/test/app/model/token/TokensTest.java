package app.model.token;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static app.model.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class TokensTest {
    @Test
    public void shouldGetTokens() {
        // given
        Tokens tokens = new Tokens(3, 2);

        // when
        int green = tokens.get(Green);
        int purple = tokens.get(Purple);
        int blue = tokens.get(Blue);
        int black = tokens.get(Black);
        int red = tokens.get(Red);
        int versatile = tokens.getVersatile();

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
        Tokens tokens = new Tokens(0, 4, 2, 12, 4);

        // when
        int green = tokens.get(Green);
        int purple = tokens.get(Purple);
        int blue = tokens.get(Blue);
        int black = tokens.get(Black);
        int red = tokens.get(Red);

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
        Tokens tokens = new Tokens(3, 2);

        // when
        Map<TokenColor, Integer> map = tokens.asMap();

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
        Tokens original = new Tokens(1, 2, 3, 4, 5);

        // when
        Tokens copy = new Tokens(original);

        // then
        assertEquals(copy, original);
    }

    @Test
    public void shouldAddTokens() {
        // given
        Tokens first = new Tokens(1, 2, 3, 4, 5, 6);
        Tokens second = new Tokens(0, 4, 4, 1, 5, 6);

        // when
        Tokens result = first.add(second);

        // then
        assertEquals(new Tokens(1, 6, 7, 5, 10, 12), result);
    }

    @Test
    public void shouldSubtractTokens() {
        // given
        Tokens first = new Tokens(1, 2, 3, 4, 5, 6);
        Tokens second = new Tokens(0, 4, 4, 1, 5, 5);

        // when
        Tokens result = first.subtract(second);

        // then
        assertEquals(new Tokens(1, -2, -1, 3, 0, 1), result);
    }

    @Test
    public void shouldReturnAsCost() {
        // given
        Tokens tokens = new Tokens(1, 2, 3, 4, 5, 6);

        // when
        Tokens cost = tokens.asCost();

        // then
        assertEquals(new Tokens(1, 2, 3, 4, 5, 0), cost);
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
        Tokens tokens = new Tokens(map, 2);

        // then
        assertEquals(new Tokens(2, 2, 2, 2, 2, 2), tokens);
    }

    @Test
    public void shouldRemoveDebts() {
        // given
        Tokens debts = new Tokens(1, -1, 0, 4, -5, -3);

        // when
        Tokens tokens = Tokens.Operations.removeDebts(debts);

        // then
        assertEquals(new Tokens(1, 0, 0, 4, 0, -3), tokens);
    }
}