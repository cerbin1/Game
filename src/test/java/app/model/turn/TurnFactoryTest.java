package app.model.turn;

import app.model.card.CheapCard;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.presenter.UnexpectedGatherException;
import app.presenter.table.Table;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static app.model.token.TokenColor.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;

public class TurnFactoryTest {
    @Rule
    public ExpectedException expectedException = none();

    private TokenVO tokenVO(TokenColor color) {
        return new TokenVO(new Token(color), 0, 0);
    }

    private static CardVO cardVO() {
        return new CardVO(new CheapCard(), 0, 0);
    }

    @Test
    public void shouldCreateBuyCardTurn() {
        // given
        Table table = new Table();
        table.put(cardVO());

        // when
        Turn turn = table.gather();

        // then
        assertThat(turn, instanceOf(BuyCardTurn.class));
    }

    @Test
    public void shouldCreateReservationTurn() {
        // given
        Table table = new Table();

        table.put(cardVO());
        table.put(tokenVO(null));

        // when
        Turn turn = table.gather();

        // then
        assertThat(turn, instanceOf(ReservationTurn.class));
    }

    @Test
    public void shouldCreateAcquireTokensTurnWithTwoSameTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));
        table.put(tokenVO(Green));

        // when
        Turn turn = table.gather();

        // then
        assertThat(turn, instanceOf(AcquireTokensTurn.class));
    }

    @Test
    public void shouldCreateAcquireTokensTurnWithThreeDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));
        table.put(tokenVO(Blue));
        table.put(tokenVO(Red));

        // when
        Turn turn = table.gather();

        // then
        assertThat(turn, instanceOf(AcquireTokensTurn.class));
    }

    @Test
    public void shouldNotCreateAcquireTokensTurnWithTwoDifferentTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));
        table.put(tokenVO(Blue));

        expectedException.expect(UnexpectedGatherException.class);

        // when
        table.gather();
    }

    @Test
    @Ignore
    public void shouldNotCreateAcquireTokensTurnWithThreeSameTokens() {
        // given
        Table table = new Table();
        table.put(tokenVO(Green));
        table.put(tokenVO(Green));
        table.put(tokenVO(Green));  // This is never succeeded

        expectedException.expect(UnexpectedGatherException.class); // so this exception is never thrown

        // when
        table.gather();
    }

    @Test
    public void shouldNotCreateTurnWithSingleToken() {
        // given
        Table table = new Table();
        table.put(tokenVO(Blue));

        expectedException.expect(UnexpectedGatherException.class);

        // when
        table.gather();
    }
}
