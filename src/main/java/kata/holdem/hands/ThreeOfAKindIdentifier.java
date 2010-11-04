package kata.holdem.hands;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class ThreeOfAKindIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		return null;
	}
}
