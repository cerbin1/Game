import app.game.token.TokenColor;
import app.game.card.CardColorGenerator;
import app.util.Random;
import org.junit.Test;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class CardColorGeneratorTest {

    @Test
    public void shouldGenerateCardColor() {
        // given
        Random random = new MockRandom(0, 1, 2, 3, 4);
        CardColorGenerator colorGenerator = new CardColorGenerator(random);

        // when
        TokenColor cardColor1 = colorGenerator.generateColor();
        TokenColor cardColor2 = colorGenerator.generateColor();
        TokenColor cardColor3 = colorGenerator.generateColor();
        TokenColor cardColor4 = colorGenerator.generateColor();
        TokenColor cardColor5 = colorGenerator.generateColor();

        // then
        assertEquals(cardColor1, Green);
        assertEquals(cardColor2, Purple);
        assertEquals(cardColor3, Blue);
        assertEquals(cardColor4, Black);
        assertEquals(cardColor5, Red);
    }
}
