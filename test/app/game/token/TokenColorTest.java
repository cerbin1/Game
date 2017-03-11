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
        TokenColor cardColor1 = TokenColor.getRandom(probability);
        TokenColor cardColor2 = TokenColor.getRandom(probability);
        TokenColor cardColor3 = TokenColor.getRandom(probability);
        TokenColor cardColor4 = TokenColor.getRandom(probability);
        TokenColor cardColor5 = TokenColor.getRandom(probability);

        // then
        assertEquals(Green, cardColor1);
        assertEquals(Purple, cardColor2);
        assertEquals(Blue, cardColor3);
        assertEquals(Black, cardColor4);
        assertEquals(Red, cardColor5);
    }
}
