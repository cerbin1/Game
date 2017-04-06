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
    private List<Correlation> correlations = new ArrayList<>();

    public TurnFactory() {
        addCombination(new BuyCardTurnFactory(), CardVO.class);
        addCombination(new ReservationTurn1Factory(), CardVO.class, TokenVO.class);
        addCombination(new ReservationTurn2Factory(), TokenVO.class, CardVO.class);
        addCombination(new AcquireTokensTurnFactory(), TokenVO.class, TokenVO.class);
        addCombination(new AcquireTokensTurnFactory(), TokenVO.class, TokenVO.class, TokenVO.class);
    }

    private void addCombination(Factory factory, Class... classes) {
        correlations.add(new Correlation(asList(classes), factory));
    }

    public Turn create(List<Tableable> tableables) {
        List<Class> classes = tableables.stream().map(Tableable::getClass).collect(toList());
        if (getCombinations().contains(classes)) {
            Optional<Correlation> first = correlations.stream().filter(setClassPair -> setClassPair.getKey().equals(classes)).findFirst();
            if (first.isPresent()) {
                return first.get().getTurn(tableables);
            }
        }
        throw new UnexpectedCreateTurnException();
    }

    private List<List<Class>> getCombinations() {
        return correlations.stream().map(Pair::getKey).collect(toList());
    }

    private class Correlation extends Pair<List<Class>, Factory> {
        Correlation(List<Class> key, Factory value) {
            super(key, value);
        }

        public Turn getTurn(List<Tableable> tableables) {
            return getValue().getTurn(tableables);
        }
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
