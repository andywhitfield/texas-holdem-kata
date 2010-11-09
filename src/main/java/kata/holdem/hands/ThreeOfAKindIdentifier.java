package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class ThreeOfAKindIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Map.Entry<Integer, Collection<Card>>> threeOfAKinds = Hands.existSameValuedCards(cards, 3);
		if (threeOfAKinds.isEmpty()) return null;
		
		// there's at least one three of a kin, pick the best one...
		List<Card> theBestThreeOfAKind = new ArrayList<Card>(3);
		theBestThreeOfAKind.addAll(threeOfAKinds.get(0).getValue());
		return new RankedHand(player, cards, this, theBestThreeOfAKind);
	}
}
