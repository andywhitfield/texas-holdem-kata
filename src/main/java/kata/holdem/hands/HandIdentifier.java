package kata.holdem.hands;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public interface HandIdentifier {
	public RankedHand accept(String player, Iterable<Card> cards);
}
