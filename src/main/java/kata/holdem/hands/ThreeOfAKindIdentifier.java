package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.KeyValue;

public class ThreeOfAKindIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<KeyValue<Integer, Collection<Card>>> threeOfAKinds = Hands.existSameValuedCards(cards, 3);
		if (threeOfAKinds.isEmpty()) return null;
		
		// there's at least one three of a kin, pick the best one...
		List<Card> theBestThreeOfAKind = new ArrayList<Card>(3);
		theBestThreeOfAKind.addAll(threeOfAKinds.get(0).getValue());
		return new RankedHand(player, cards, this, theBestThreeOfAKind);
	}
}
