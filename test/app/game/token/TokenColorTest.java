package app.game.token;

import org.junit.Test;
import testUtils.MockProbability;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class TokenColorTest {
    @Test
    public void shouldGetRandomCardColor() {
        // given
        MockProbability probability = new MockProbability(0, 1, 2, 3, 4);

        // when
        TokenColor color1 = TokenColor.getRandom(probability);
        TokenColor color2 = TokenColor.getRandom(probability);
        TokenColor color3 = TokenColor.getRandom(probability);
        TokenColor color4 = TokenColor.getRandom(probability);
        TokenColor color5 = TokenColor.getRandom(probability);

        // then
        assertEquals(Green, color1);
        assertEquals(Purple, color2);
        assertEquals(Blue, color3);
        assertEquals(Black, color4);
        assertEquals(Red, color5);
    }
}
