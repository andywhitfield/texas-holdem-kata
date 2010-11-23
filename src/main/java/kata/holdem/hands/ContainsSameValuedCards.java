package kata.holdem.hands;

import java.util.Collection;

import kata.holdem.Card;
import kata.holdem.collections.KeyValue;
import kata.holdem.collections.Predicate;
import kata.holdem.hands.Hands.SameValueMatchType;

class ContainsSameValuedCards implements Predicate<KeyValue<Integer, Collection<Card>>> {
	private final int numberOfMatches;
	private final SameValueMatchType matchType;
	
	public ContainsSameValuedCards(int numberOfMatches, SameValueMatchType matchType) {
		this.numberOfMatches = numberOfMatches;
		this.matchType = matchType;
	}
	
	@Override
	public boolean evaluate(KeyValue<Integer, Collection<Card>> cardsWithTheSameValue) {
		switch (matchType) {
		case Exact:
			return cardsWithTheSameValue.getValue().size() == numberOfMatches;
		case Minimum:
			return cardsWithTheSameValue.getValue().size() >= numberOfMatches;
		default:
			throw new IllegalArgumentException("Unknown matchType: " + matchType);
		}
	}
}