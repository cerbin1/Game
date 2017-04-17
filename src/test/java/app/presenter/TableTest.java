package app.presenter;

import app.model.card.CheapCard;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.model.token.TokensAmount;
import app.presenter.table.Table;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static app.model.token.TokenColor.*;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.rules.ExpectedException.none;

public class TableTest {
    @Rule
    public ExpectedException expectedException = none();

    private static CardVO cardVO() {
        return new CardVO(new CheapCard(new TokensAmount(), 0, Green), 0, 0);
    }

    private static TokenVO tokenVO() {
        return tokenVO(Green);
    }

    private static TokenVO versatileVO() {
        return new TokenVO(new Token(null), 0, 0);
    }

    private static TokenVO tokenVO(TokenColor color) {
        return new TokenVO(new Token(color), 0, 0);
    }

    @Test
    public void shouldPutCard() {
        // given
        Table table = new Table();

        // when
        boolean canPut = table.put(cardVO());

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldPutVersatile() {
        // given
        Table table = new Table();

        // when
        boolean canPut = table.put(versatileVO());

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldNotPutSecondCard() {
        // given
        Table table = new Table();
        table.put(cardVO());

        // when
        boolean canPut = table.put(cardVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldPutCardOnVersatile() {
        // given
        Table table = new Table();
        table.put(versatileVO());

        // when
        boolean canPut = table.put(cardVO());

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldPutVersatileOnCard() {
        // given
        Table table = new Table();
        table.put(cardVO());

        // when
        boolean canPut = table.put(versatileVO());

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldNotPutCardOnToken() {
        // given
        Table table = new Table();
        table.put(tokenVO());

        // when
        boolean canPut = table.put(cardVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutTokenOnCard() {
        // given
        Table table = new Table();
        table.put(cardVO());

        // when
        boolean canPut = table.put(tokenVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutSecondVersatile() {
        // given
        Table table = new Table();
        table.put(versatileVO());

        // when
        boolean canPut = table.put(versatileVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutVersatileOnToken() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));

        // when
        boolean canPut = table.put(versatileVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutTokenOnVersatile() {
        // given
        Table table = new Table();
        table.put(versatileVO());

        // when
        boolean canPut = table.put(tokenVO(Green));

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldPutTwoSameTokens() {
        // given
        Table table = new Table();

        // when
        boolean canPut1 = table.put(tokenVO(Green));
        boolean canPut2 = table.put(tokenVO(Green));

        // then
        assertTrue(canPut1);
        assertTrue(canPut2);
    }

    @Test
    public void shouldNotPutThirdTokenSameColor() {
        // given
        Table table = new Table();
        table.put(tokenVO(Red));
        table.put(tokenVO(Red));

        // when
        boolean canPut = table.put(tokenVO(Blue));

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutThirdTokenAlreadyPut() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));
        table.put(tokenVO(Blue));

        // when
        boolean canPut = table.put(tokenVO(Green));

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldPutThreeDifferentTokens() {
        // given
        Table table = new Table();

        // when
        boolean canPut1 = table.put(tokenVO(Green));
        boolean canPut2 = table.put(tokenVO(Blue));
        boolean canPut3 = table.put(tokenVO(Black));

        // then
        assertTrue(canPut1);
        assertTrue(canPut2);
        assertTrue(canPut3);
    }

    @Test
    public void shouldNotPutFourthToken() {
        // given
        Table table = new Table();

        // when
        boolean canPut1 = table.put(tokenVO(Green));
        boolean canPut2 = table.put(tokenVO(Blue));
        boolean canPut3 = table.put(tokenVO(Black));
        boolean canPut4 = table.put(tokenVO(Red));

        // then
        assertTrue(canPut1);
        assertTrue(canPut2);
        assertTrue(canPut3);
        assertFalse(canPut4);
    }

    @Test
    public void shouldThrowOnTakingNotExistingTableable() {
        // given
        Table table = new Table();

        expectedException.expect(UnexpectedTakeException.class);

        // when
        table.take(cardVO());
    }

    @Test
    public void shouldNotGatherEmpty() {
        // given
        Table table = new Table();

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldGatherCard() {
        // given
        Table table = new Table();
        table.put(cardVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldGatherVersatileWithCard() {
        // given
        Table table = new Table();
        table.put(cardVO());
        table.put(versatileVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotGatherVersatile() {
        // given
        Table table = new Table();
        table.put(versatileVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldNotGatherSingleToken() {
        // given
        Table table = new Table();
        table.put(tokenVO());

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldGatherTwoSameTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Blue));
        table.put(tokenVO(Blue));

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotGatherTwoDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Blue));
        table.put(tokenVO(Green));

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldGatherThreeDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Blue));
        table.put(tokenVO(Green));
        table.put(tokenVO(Purple));

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldGather() {
        // given
        Table table = new Table();
        Tableable cardVO = cardVO(), tokenVO = versatileVO();

        table.put(cardVO);
        table.put(tokenVO);

        // when
        List<Tableable> gathered = table.gather();

        // then
        assertEquals(asList(cardVO, tokenVO), gathered);
    }

    @Test
    public void shouldThrowOnUnexpectedGather() {
        // given
        Table table = new Table();

        expectedException.expect(UnexpectedGatherException.class);

        // when
        table.gather();
    }
}
