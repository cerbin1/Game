package app.model.turn;

import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;

import java.util.List;

public class TurnFactory {
    public TurnFactory() {
    }

    public static class BuyCardTurnFactory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            CardVO cardVO = (CardVO) tableables.get(0);
            return new BuyCardTurn(cardVO.getCard());
        }
    }

    public static class ReservationTurn1Factory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            CardVO cardVO = (CardVO) tableables.get(0); // TODO implement
            return new ReservationTurn(cardVO.getCard());
        }
    }

    public static class AcquireTwoTokensTurnFactory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            if (tableables.size() == 2) {
                if (((TokenVO) tableables.get(0)).getColor() == ((TokenVO) tableables.get(1)).getColor()) {
                    return new AcquireTokensTurn(null);
                }
            }
            throw new UnexpectedCreateTurnException();
        }
    }

    public static class AcquireThreeTokensTurnFactory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            if (tableables.size() == 3) {
                long uniqueTokens = tableables.stream()
                        .map(tableable -> (TokenVO) tableable)
                        .map(TokenVO::getColor)
                        .distinct()
                        .count();

                if (uniqueTokens == 3) {
                    return new AcquireTokensTurn(null);
                }
            }
            throw new UnexpectedCreateTurnException();
        }
    }
}
