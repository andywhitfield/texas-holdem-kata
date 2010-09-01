package kata.holdem.hands;

import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public interface HandIdentifier {
	public RankedHand accept(String player, List<Card> cards);
}
