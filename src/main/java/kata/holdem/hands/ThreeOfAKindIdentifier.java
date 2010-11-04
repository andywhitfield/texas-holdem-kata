package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.Predicate;

public class ThreeOfAKindIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		// group cards by card value...
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<Map.Entry<Integer, Collection<Card>>> threeOfAKinds = Iterables.where(groupedByValue.entrySet(), new ContainsAtLeastOneThreeOfAKind());
		
		if (threeOfAKinds.isEmpty()) return null;
		
		// there's at least one three of a kin, pick the best one...
		List<Card> theBestThreeOfAKind = new ArrayList<Card>(3);
		List<Map.Entry<Integer, Collection<Card>>> sortedPairs = Iterables.sort(threeOfAKinds, new Comparator<Map.Entry<Integer, Collection<Card>>>() {
			@Override
			public int compare(Entry<Integer, Collection<Card>> o1, Entry<Integer, Collection<Card>> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}});
		theBestThreeOfAKind.addAll(sortedPairs.get(0).getValue());
		return new RankedHand(player, cards, this, theBestThreeOfAKind);
	}

	private static class ContainsAtLeastOneThreeOfAKind implements Predicate<Map.Entry<Integer, Collection<Card>>> {
		@Override
		public boolean evaluate(Map.Entry<Integer, Collection<Card>> cardsWithTheSameValue) {
			return cardsWithTheSameValue.getValue().size() == 3;
		}
	}
}
