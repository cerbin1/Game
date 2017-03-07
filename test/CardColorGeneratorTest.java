import app.game.token.TokenColor;
import app.game.card.CardColorGenerator;
import org.junit.Test;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class CardColorGeneratorTest {

    @Test
    public void shouldGenerateCardColor() {
        // given
        CardColorGenerator colorGenerator = new CardColorGenerator(new MockProbability(0, 1, 2, 3, 4));

        // when
        TokenColor cardColor1 = colorGenerator.generateColor();
        TokenColor cardColor2 = colorGenerator.generateColor();
        TokenColor cardColor3 = colorGenerator.generateColor();
        TokenColor cardColor4 = colorGenerator.generateColor();
        TokenColor cardColor5 = colorGenerator.generateColor();

        // then
        assertEquals(Green, cardColor1);
        assertEquals(Purple, cardColor2);
        assertEquals(Blue, cardColor3);
        assertEquals(Black, cardColor4);
        assertEquals(Red, cardColor5);
    }
}
