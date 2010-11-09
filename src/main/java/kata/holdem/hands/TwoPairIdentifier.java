package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class TwoPairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Map.Entry<Integer, Collection<Card>>> pairs = Hands.existSameValuedCards(cards, 2);
		if (pairs.size() < 2) return null;
		
		// there's at least two pairs, pick the best 2 pairs...
		List<Card> thePairs = new ArrayList<Card>(4);
		thePairs.addAll(pairs.get(0).getValue());
		thePairs.addAll(pairs.get(1).getValue());
		return new RankedHand(player, cards, this, thePairs);
	}
}
