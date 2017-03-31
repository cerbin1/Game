package app.presenter;

import app.model.card.CheapCard;
import app.model.token.Token;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import org.junit.Test;

import java.awt.*;

import static app.model.token.TokenColor.Green;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionTableTest {
    private static PositionTable positionTable() {
        return new PositionTable(0, 0);
    }

    private static CardVO cardVO() {
        return new CardVO(new CheapCard(), 0, 0);
    }

    private static TokenVO tokenVO() {
        return new TokenVO(new Token(Green), 0, 0);
    }

    private static Point point() {
        return new Point(0, 0);
    }

    @Test
    public void shouldPutTableable() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();

        // when
        boolean didPut = table.put(cardVO);

        // then
        assertTrue(didPut);
        assertTrue(table.has(cardVO));
    }

    @Test
    public void shouldNotPutTableable() {
        // given
        PositionTable table = positionTable();
        table.put(cardVO());
        CardVO cardVO = cardVO();

        // when
        boolean didPut = table.put(cardVO);

        // then
        assertFalse(didPut);
        assertFalse(table.has(cardVO));
    }

    @Test
    public void shouldRemoveTableableAfterTake() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();
        table.put(cardVO);

        // when
        boolean didTake = table.take(cardVO);

        // then
        assertTrue(didTake);
        assertTrue(table.isEmpty());
    }
}
