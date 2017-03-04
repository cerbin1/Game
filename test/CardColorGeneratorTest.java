import app.MockRandom;
import app.game.token.TokenColor;
import app.game.card.CardColorGenerator;
import app.util.Random;
import org.junit.Test;

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
        assertEquals(cardColor1, TokenColor.Green);
        assertEquals(cardColor2, TokenColor.Purple);
        assertEquals(cardColor3, TokenColor.Blue);
        assertEquals(cardColor4, TokenColor.Black);
        assertEquals(cardColor5, TokenColor.Red);
    }
}
