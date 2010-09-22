package kata.holdem.hands;

import java.util.Collections;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class HighCardIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, List<Card> cards) {
		return new RankedHand(player, 0, cards, Collections.<Card>emptyList());
	}
}
