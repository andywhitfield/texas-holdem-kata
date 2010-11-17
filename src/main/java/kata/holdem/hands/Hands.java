package kata.holdem.hands;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kata.holdem.Card;
import kata.holdem.collections.Iterables;

public class Hands {
	public enum SameValueMatchType { Exact, Minimum }

	public static List<Map.Entry<Integer, Collection<Card>>> existSameValuedCards(Iterable<Card> cards, int numberOfSameValuedCards) {
		return existSameValuedCards(cards, numberOfSameValuedCards, SameValueMatchType.Exact);
	}
	public static List<Map.Entry<Integer, Collection<Card>>> existSameValuedCards(Iterable<Card> cards, int numberOfSameValuedCards, SameValueMatchType matchType) {
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<Map.Entry<Integer, Collection<Card>>> sameValuedCards = Iterables.where(groupedByValue.entrySet(), new ContainsSameValuedCards(numberOfSameValuedCards, matchType));
		
		return Iterables.sort(sameValuedCards, new Comparator<Map.Entry<Integer, Collection<Card>>>() {
			@Override
			public int compare(Entry<Integer, Collection<Card>> o1, Entry<Integer, Collection<Card>> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}});
	}
}
