package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.Predicate;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		// group cards by card value...
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<Collection<Card>> pairs = Iterables.where(groupedByValue.values(), new ContainsAtLeastOnePair());
		
		if (pairs.size() != 1) return null;
		
		// there's a pair
		return new RankedHand(player, cards, this, new ArrayList<Card>(pairs.iterator().next()));
	}

	private static class ContainsAtLeastOnePair implements Predicate<Collection<Card>> {
		@Override
		public boolean evaluate(Collection<Card> cardsWithTheSameValue) {
			return cardsWithTheSameValue.size() == 2;
		}
	}
}
