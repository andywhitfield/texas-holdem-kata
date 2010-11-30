package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.KeyValue;

public class FourOfAKindIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<KeyValue<Integer, Collection<Card>>> fourOfAKind = Hands.existSameValuedCards(cards, 4);
		if (fourOfAKind.isEmpty()) return null;
		
		return new RankedHand(player, cards, this, new ArrayList<Card>(fourOfAKind.get(0).getValue()));
	}
}
