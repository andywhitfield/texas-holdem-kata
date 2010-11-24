package kata.holdem.hands;

import kata.holdem.Card;
import kata.holdem.Suit;
import kata.holdem.collections.Action;

class CardSuit implements Action<Card, Suit> {
	@Override
	public Suit action(Card card) {
		return card.getSuit();
	}
}