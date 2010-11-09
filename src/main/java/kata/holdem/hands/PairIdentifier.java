package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Map.Entry<Integer, Collection<Card>>> pairs = Hands.existSameValuedCards(cards, 2);
		if (pairs.size() != 1) return null;
		
		// there's a pair
		return new RankedHand(player, cards, this, new ArrayList<Card>(pairs.get(0).getValue()));
	}
}
