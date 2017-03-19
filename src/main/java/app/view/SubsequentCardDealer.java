package app.view;

import app.view.render.CardVO;

import java.util.List;
import java.util.function.Function;

public class SubsequentCardDealer {
    private final List<CardVO> cardVOs;
    private final Function<Integer, Integer> xValue;

    private int amount, current = 0;

    public SubsequentCardDealer(List<CardVO> cardVOs, int amount, Function<Integer, Integer> xValue) {
        this.cardVOs = cardVOs;
        this.xValue = xValue;
        this.amount = amount;
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount lower than 0");
        }
    }

    public void deal() {
        if (current < cardVOs.size()) {
            dealCurrentCard();
            current++;
        }
    }

    private void dealCurrentCard() {
        CardVO vo = getCurrentCardVO();
        vo.moveXConstantSpeed(xValue.apply(current % amount), 1.0, this::deal);
        vo.setFlipped(true);
    }

    private CardVO getCurrentCardVO() {
        return cardVOs.get(cardVOs.size() - 1 - current);
    }
}
