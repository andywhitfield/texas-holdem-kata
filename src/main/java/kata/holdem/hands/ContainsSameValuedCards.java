package kata.holdem.hands;

import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.collections.Predicate;
import kata.holdem.hands.Hands.SameValueMatchType;

class ContainsSameValuedCards implements Predicate<Map.Entry<Integer, Collection<Card>>> {
	private final int numberOfMatches;
	private final SameValueMatchType matchType;
	
	public ContainsSameValuedCards(int numberOfMatches, SameValueMatchType matchType) {
		this.numberOfMatches = numberOfMatches;
		this.matchType = matchType;
	}
	
	@Override
	public boolean evaluate(Map.Entry<Integer, Collection<Card>> cardsWithTheSameValue) {
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