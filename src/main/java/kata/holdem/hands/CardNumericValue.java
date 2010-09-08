package kata.holdem.hands;

import kata.holdem.Card;
import kata.holdem.collections.Action;

class CardNumericValue implements Action<Card, Integer> {
	@Override
	public Integer action(Card card) {
		return card.getNumericValue();
	}
}