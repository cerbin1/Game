import app.game.token.TokenColor;
import app.game.token.Tokens;
import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class TokensTest {
    @Test
    public void shouldGetTokens() {
        // given
        Tokens tokens = new Tokens(3, 2);

        // when
        int green = tokens.getGreen();
        int purple = tokens.getPurple();
        int blue = tokens.getBlue();
        int black = tokens.getBlack();
        int red = tokens.getRed();
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
        int green = tokens.getGreen();
        int purple = tokens.getPurple();
        int blue = tokens.getBlue();
        int black = tokens.getBlack();
        int red = tokens.getRed();

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
}