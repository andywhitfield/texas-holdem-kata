package kata.holdem.hands;

import java.util.ArrayList;
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
		
		if (pairs.size() != 1) return null;
		
		// there's a pair
		List<Card> thePair = new ArrayList<Card>(2);
		thePair.addAll(pairs.iterator().next());
		return new RankedHand(player, 1, thePair, Iterables.where(cards, new IsNotIn(pairs.iterator().next())));
	}

	private static class ContainsAtLeastOnePair implements Predicate<Collection<Card>> {
		@Override
		public boolean evaluate(Collection<Card> cardsWithTheSameValue) {
			return cardsWithTheSameValue.size() == 2;
		}
	}

	private static class IsNotIn implements Predicate<Card> {
		private final Collection<Card> inCards;

		public IsNotIn(Collection<Card> inCards) {
			this.inCards = inCards;
		}

		@Override
		public boolean evaluate(Card item) {
			return !inCards.contains(item);
		}
	}
}
