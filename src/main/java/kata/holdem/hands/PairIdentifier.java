package kata.holdem.hands;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.Predicate;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, List<Card> cards) {
		// group cards by card value...
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<Collection<Card>> pairs = Iterables.where(groupedByValue.values(), new ContainsAtLeastOnePair());
		
		return pairs.isEmpty() ? null : new RankedHand(player, 1, cards);
	}

	private static class ContainsAtLeastOnePair implements Predicate<Collection<Card>> {
		@Override
		public boolean evaluate(Collection<Card> cardsWithTheSameValue) {
			return cardsWithTheSameValue.size() >= 2;
		}
	}
}
