package kata.holdem.hands;

import java.util.Collections;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class HighCardIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		return new RankedHand(player, cards, 0, Collections.<Card>emptyList());
	}
}
