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
        assertEquals(green, 3);
        assertEquals(purple, 3);
        assertEquals(blue, 3);
        assertEquals(black, 3);
        assertEquals(red, 3);
        assertEquals(versatile, 2);
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
        assertEquals(green, 0);
        assertEquals(purple, 4);
        assertEquals(blue, 2);
        assertEquals(black, 12);
        assertEquals(red, 4);
    }

    @Test
    public void shouldGetTokensAsMap() {
        // given
        Tokens tokens = new Tokens(3, 2);

        // when
        Map<TokenColor, Integer> map = tokens.asMap();

        // then
        assertEquals(map, new EnumMap<TokenColor, Integer>(TokenColor.class) {{
            put(Green, 3);
            put(Purple, 3);
            put(Blue, 3);
            put(Black, 3);
            put(Red, 3);
        }});
    }
}