package app.model.turn;

import app.model.token.TokenColor;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class TurnFactory {
    private List<Pair<List<Class>, Factory>> combinations = new ArrayList<>();

    public TurnFactory() {
        combinations.add(new Pair<>(new ArrayList<>(asList(CardVO.class)), new BuyCardTurnFactory()));
        combinations.add(new Pair<>(new ArrayList<>(asList(CardVO.class, TokenVO.class)), new ReservationTurn1Factory()));
        combinations.add(new Pair<>(new ArrayList<>(asList(TokenVO.class, CardVO.class)), new ReservationTurn2Factory()));
        combinations.add(new Pair<>(new ArrayList<>(asList(TokenVO.class, TokenVO.class)), new AcquireTokensTurnFactory()));
        combinations.add(new Pair<>(new ArrayList<>(asList(TokenVO.class, TokenVO.class, TokenVO.class)), new AcquireTokensTurnFactory()));
    }

    public Turn create(List<Tableable> tableables) {
        List<Class> list = tableables.stream().map(Tableable::getClass).collect(toList());
        if (combinations.stream().map(Pair::getKey).collect(toList()).contains(new ArrayList<>(list))) {
            Optional<Pair<List<Class>, Factory>> first = combinations.stream().filter(setClassPair -> setClassPair.getKey().equals(new ArrayList<>(list))).findFirst();
            if (first.isPresent()) {
                return first.get().getValue().getTurn(tableables);
            }
        }
        throw new UnexpectedCreateTurnException();
    }

    private class BuyCardTurnFactory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            CardVO cardVO = (CardVO) tableables.get(0);
            return new BuyCardTurn(cardVO.getCard());
        }
    }

    private class ReservationTurn1Factory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            CardVO cardVO = (CardVO) tableables.get(0);
            return new ReservationTurn(cardVO.getCard());
        }
    }

    private class ReservationTurn2Factory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            CardVO cardVO = (CardVO) tableables.get(1);
            return new ReservationTurn(cardVO.getCard());
        }
    }

    private class AcquireTokensTurnFactory implements Factory {
        @Override
        public Turn getTurn(List<Tableable> tableables) {
            if (tableables.size() == 2) {
                if (((TokenVO) tableables.get(0)).getColor() == ((TokenVO) tableables.get(1)).getColor()) {
                    return new AcquireTokensTurn(null);
                }
            }
            if (tableables.size() == 3) {
                TokenColor color1 = ((TokenVO) tableables.get(0)).getColor();
                TokenColor color2 = ((TokenVO) tableables.get(1)).getColor();
                TokenColor color3 = ((TokenVO) tableables.get(2)).getColor();
                if (color1 != color2 && color2 != color3 && color1 != color3) {
                    return new AcquireTokensTurn(null);
                }
            }
            throw new UnexpectedCreateTurnException();
        }
    }
}
