package kata.holdem.hands;

import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.collections.Predicate;

class ContainsSameValuedCards implements Predicate<Map.Entry<Integer, Collection<Card>>> {
	private final int numberOfMatches;
	
	public ContainsSameValuedCards(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}
	
	@Override
	public boolean evaluate(Map.Entry<Integer, Collection<Card>> cardsWithTheSameValue) {
		return cardsWithTheSameValue.getValue().size() == numberOfMatches;
	}
}