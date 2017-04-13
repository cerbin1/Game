package app.model.turn;

import app.model.card.CheapCard;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static app.model.token.TokenColor.*;
import static java.util.Arrays.asList;
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
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = new ArrayList<>();
        tableables.add(cardVO());

        // when
        Turn turn = turnFactory.create(tableables);

        // then
        assertThat(turn, instanceOf(BuyCardTurn.class));
    }

    @Test
    public void shouldCreateReservationTurn() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = new ArrayList<>();
        tableables.add(cardVO());
        tableables.add(tokenVO(null));

        // when
        Turn turn = turnFactory.create(tableables);

        // then
        assertThat(turn, instanceOf(ReservationTurn.class));
    }

    @Test
    public void shouldCreateAcquireTokensTurnWithTwoSameTokens() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = asList(tokenVO(Green), tokenVO(Green));

        // when
        Turn turn = turnFactory.create(tableables);

        // then
        assertThat(turn, instanceOf(AcquireTokensTurn.class));
    }

    @Test
    public void shouldCreateAcquireTokensTurnWithThreeDifferentTokens() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = asList(tokenVO(Green), tokenVO(Blue), tokenVO(Red));

        // when
        Turn turn = turnFactory.create(tableables);

        // then
        assertThat(turn, instanceOf(AcquireTokensTurn.class));
    }

    @Test
    public void shouldNotCreateAcquireTokensTurnWithTwoDifferentTokens() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = asList(tokenVO(Green), tokenVO(Blue));

        expectedException.expect(UnexpectedCreateTurnException.class);

        // when
        turnFactory.create(tableables);
    }

    @Test
    public void shouldNotCreateAcquireTokensTurnWithThreeSameTokens() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = asList(tokenVO(Green), tokenVO(Green), tokenVO(Green));

        expectedException.expect(UnexpectedCreateTurnException.class);

        // when
        turnFactory.create(tableables);
    }

    @Test
    public void shouldNotCreateTurnWithSingleToken() {
        // given
        TurnFactory turnFactory = new TurnFactory();
        List<Tableable> tableables = new ArrayList<>();
        tableables.add(tokenVO(Blue));

        expectedException.expect(UnexpectedCreateTurnException.class);

        // when
        turnFactory.create(tableables);
    }
}
