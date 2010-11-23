package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.KeyValue;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<KeyValue<Integer, Collection<Card>>> pairs = Hands.existSameValuedCards(cards, 2);
		if (pairs.size() != 1) return null;
		
		// there's a pair
		return new RankedHand(player, cards, this, new ArrayList<Card>(pairs.get(0).getValue()));
	}
}
