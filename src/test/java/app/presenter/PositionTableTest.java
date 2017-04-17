package app.presenter;

import app.model.card.CheapCard;
import app.model.token.Token;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;

import static app.model.token.TokenColor.Green;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.rules.ExpectedException.none;

public class PositionTableTest {
    @Rule
    public ExpectedException expectedException = none();

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
    public void shouldRemoveTableablePositionAfterTake() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();
        table.put(cardVO);

        // when
        table.take(cardVO);

        // then
        assertTrue(table.isEmpty());
    }

    @Test
    public void shouldThrowOnUnexpectedTake() {
        // given
        PositionTable table = positionTable();
        CardVO cardVO = cardVO();

        expectedException.expect(UnexpectedTakeException.class);

        // when
        table.take(cardVO);
    }

    @Test
    public void shouldBeAbleToGather() {
        // given
        PositionTable table = positionTable();
        table.put(cardVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotBeAbleToGather() {
        // given
        PositionTable table = positionTable();
        table.put(tokenVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldGather() {
        // given
        PositionTable table = positionTable();
        table.put(cardVO());

        // when
        table.gather();

        // then
        assertTrue(table.isEmpty());
    }
}
