import game.TokenColor;
import game.Tokens;
import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TokensTest {
    @Test
    public void shouldGetGreen() throws Exception {
        // given
        Tokens tokens = new Tokens(3, 2);

        // when
        Map<TokenColor, Integer> map = tokens.asMap();

        // then
        assertEquals(map, new EnumMap<TokenColor, Integer>(TokenColor.class) {{
            put(TokenColor.Green, 3);
            put(TokenColor.Purple, 3);
            put(TokenColor.Blue, 3);
            put(TokenColor.Black, 3);
            put(TokenColor.Red, 3);
        }});
    }
}