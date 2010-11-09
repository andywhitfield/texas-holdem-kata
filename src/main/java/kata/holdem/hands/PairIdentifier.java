package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Iterables;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		// group cards by card value...
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<Map.Entry<Integer, Collection<Card>>> pairs = Iterables.where(groupedByValue.entrySet(), new ContainsSameValuedCards(2));
		
		if (pairs.size() != 1) return null;
		
		// there's a pair
		return new RankedHand(player, cards, this, new ArrayList<Card>(pairs.iterator().next().getValue()));
	}
}
