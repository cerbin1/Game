package app.presenter;

import app.model.card.CheapCard;
import app.view.render.vo.CardVO;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PositionTableTest {
    private static PositionTable positionTable() {
        return new PositionTable(0, 0);
    }

    private static CardVO cardVO() {
        return new CardVO(new CheapCard(), 0, 0);
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
        boolean canPut = table.put(cardVO);

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldNotPutTableable() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO1 = cardVO();
        CardVO cardVO2 = cardVO();

        // when
        boolean canPut1 = table.put(cardVO1);
        boolean canPut2 = table.put(cardVO2);

        // then
        assertTrue(canPut1);
        assertFalse(canPut2);
    }

    @Test
    public void shouldAddToPositionTableAfterPut() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();

        // when
        table.put(cardVO);

        // then
        assertTrue(table.has(cardVO));
    }

    @Test
    public void shouldAddTableablePoint() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = new CardVO(new CheapCard(), 0, 0);

        // when
        table.put(cardVO);

        // then
        assertEquals(new Point(0, 0), table.getPoint(cardVO));
    }

    @Test
    public void shouldAddTableableWhenTake() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();

        // when
        table.put(cardVO);

        // then
        assertTrue(table.has(cardVO));
    }

    @Test
    public void shouldRemoveTableablePositionWhenTake() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();
        table.put(cardVO);

        // when
        table.take(cardVO);

        // then
        assertTrue(table.getPositionsTable().isEmpty());
    }

    @Test
    public void shouldTakeTableable() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();
        table.put(cardVO);

        // when
        table.take(cardVO);

        // then
        assertFalse(table.has(cardVO));
        assertEquals(null, table.getPoint(cardVO));
    }
}
