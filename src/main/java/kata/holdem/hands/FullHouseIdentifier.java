package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.KeyValue;

public class FullHouseIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<KeyValue<Integer, Collection<Card>>> threeOfAKinds = Hands.existSameValuedCards(cards, 3);
		if (threeOfAKinds.isEmpty()) return null;
		
		List<Card> rankedCards = new ArrayList<Card>(5);
		rankedCards.addAll(threeOfAKinds.get(0).getValue());

		if (threeOfAKinds.size() == 2) {
			// we have two 3-of-a-kinds. Add two of the cards from the second
			// 3-of-a-kind to complete our full house
			rankedCards.addAll(threeOfAKinds.get(1).getValue());
			rankedCards.remove(rankedCards.size() - 1);
			return new RankedHand(player, cards, this, rankedCards);
		}
		
		List<KeyValue<Integer, Collection<Card>>> pairs = Hands.existSameValuedCards(cards, 2);
		if (pairs.isEmpty()) return null;

		rankedCards.addAll(pairs.get(0).getValue());
		return new RankedHand(player, cards, this, rankedCards);
	}
}
