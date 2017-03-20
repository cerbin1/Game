package app.presenter;

import app.model.card.CheapCard;
import app.model.card.nobility.Nobility;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.model.token.Tokens;
import app.view.render.CardVO;
import app.view.render.NobilityVO;
import app.view.render.TokenVO;
import app.view.render.ViewObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static app.model.token.TokenColor.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

public class TableTest {
    @Rule
    public ExpectedException expectedException = none();

    private static CardVO cardVO() {
        return new CardVO(new CheapCard(new Tokens(), 0, Green), 0, 0);
    }

    private static ViewObject nobilityVo() {
        return new NobilityVO(new Nobility(new Tokens(), 0), 0, 0);
    }

    private static ViewObject tokenVo() {
        return tokenVo(Green);
    }

    private static ViewObject tokenVo(TokenColor color) {
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
    public void shouldPutTwoSameTokens() {
        // given
        Table table = new Table();

        // when
        boolean canPut1 = table.put(tokenVo(Green));
        boolean canPut2 = table.put(tokenVo(Green));

        // then
        assertTrue(canPut1);
        assertTrue(canPut2);
    }

    @Test
    public void shouldPutThreeDifferentTokens() {
        // given
        Table table = new Table();

        // when
        boolean canPut1 = table.put(tokenVo(Green));
        boolean canPut2 = table.put(tokenVo(Blue));
        boolean canPut3 = table.put(tokenVo(Black));

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
        boolean canPut1 = table.put(tokenVo(Green));
        boolean canPut2 = table.put(tokenVo(Blue));
        boolean canPut3 = table.put(tokenVo(Black));
        boolean canPut4 = table.put(tokenVo(Red));

        // then
        assertTrue(canPut1);
        assertTrue(canPut2);
        assertTrue(canPut3);
        assertFalse(canPut4);
    }

    @Test
    public void shouldNotPutThirdTokenSameColor() {
        // given
        Table table = new Table();
        table.put(tokenVo(Green));
        table.put(tokenVo(Green));

        // when
        boolean canPut = table.put(tokenVo(Blue));

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutThirdTokenAlreadyPut() {
        // given
        Table table = new Table();
        table.put(tokenVo(Green));
        table.put(tokenVo(Blue));

        // when
        boolean canPut = table.put(tokenVo(Green));

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldNotPutCardOnToken() {
        // given
        Table table = new Table();
        table.put(tokenVo());

        // when
        boolean canPut = table.put(cardVO());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldPutCardOnVersatile() {
        // given
        Table table = new Table();
        table.put(tokenVo(null));

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
        boolean canPut = table.put(tokenVo(null));

        // then
        assertTrue(canPut);
    }

    @Test
    public void shouldNotPutNobility() {
        // given
        Table table = new Table();

        // when
        boolean canPut = table.put(nobilityVo());

        // then
        assertFalse(canPut);
    }

    @Test
    public void shouldGather() {
        // given
        Table table = new Table();
        ViewObject cardVO = cardVO(), tokenVo = tokenVo(null);

        table.put(cardVO);
        table.put(tokenVo);

        // when
        List<ViewObject> gathered = table.gather();

        // then
        assertEquals(gathered, asList(cardVO, tokenVo));
    }

    @Test
    public void shouldThrowOnUnexpectedGather() {
        // given
        Table table = new Table();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Unexpected gather");

        // when
        table.gather();
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
    public void shouldGatherSingleToken() {
        // given
        Table table = new Table();
        table.put(tokenVo());

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotGatherTwoDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVo(Blue));
        table.put(tokenVo(Green));

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

    @Test
    public void shouldGatherTwoSameTokens() {
        // given
        Table table = new Table();
        table.put(tokenVo(Blue));
        table.put(tokenVo(Blue));

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldGatherThreeDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVo(Blue));
        table.put(tokenVo(Green));
        table.put(tokenVo(Red));

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotGatherVersatileWithCard() {
        // given
        Table table = new Table();
        table.put(cardVO());
        table.put(tokenVo(null));

        // when
        boolean canGather = table.canGather();

        // then
        assertTrue(canGather);
    }

    @Test
    public void shouldNotGatherVersatile() {
        // given
        Table table = new Table();
        table.put(tokenVo(null));

        // when
        boolean canGather = table.canGather();

        // then
        assertFalse(canGather);
    }

}
