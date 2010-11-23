package kata.holdem.hands;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.KeyValue;

public class Hands {
	public enum SameValueMatchType { Exact, Minimum }

	public static List<KeyValue<Integer, Collection<Card>>> existSameValuedCards(Iterable<Card> cards, int numberOfSameValuedCards) {
		return existSameValuedCards(cards, numberOfSameValuedCards, SameValueMatchType.Exact);
	}
	public static List<KeyValue<Integer, Collection<Card>>> existSameValuedCards(Iterable<Card> cards, int numberOfSameValuedCards, SameValueMatchType matchType) {
		Map<Integer, Collection<Card>> groupedByValue = Iterables.groupBy(cards, new CardNumericValue());
		Collection<KeyValue<Integer, Collection<Card>>> sameValuedCards = Iterables.where(Iterables.keyValues(groupedByValue), new ContainsSameValuedCards(numberOfSameValuedCards, matchType));
		
		return Iterables.sort(sameValuedCards, new Comparator<KeyValue<Integer, Collection<Card>>>() {
			@Override
			public int compare(KeyValue<Integer, Collection<Card>> o1, KeyValue<Integer, Collection<Card>> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}});
	}
}
