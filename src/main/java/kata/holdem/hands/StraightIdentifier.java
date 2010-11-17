package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Action;
import kata.holdem.collections.Iterables;

public class StraightIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Map.Entry<Integer, Collection<Card>>> inOrder = Hands.existSameValuedCards(cards, 1);
		RankedHand straight = findStraight(player, cards, inOrder);
		if (straight != null) return straight;
		return findStraight(player, cards, aceLow(inOrder));
	}

	private RankedHand findStraight(String player, Iterable<Card> cards, List<Map.Entry<Integer, Collection<Card>>> cardsByValue) {
		while (cardsByValue.size() >= 5) {
			if (cardsByValue.get(0).getKey() - 4 == cardsByValue.get(4).getKey())
				return new RankedHand(player, cards, this, cardsFrom(cardsByValue));
			cardsByValue.remove(0);
		}
		return null;
	}
	
	private List<Map.Entry<Integer, Collection<Card>>> aceLow(List<Map.Entry<Integer, Collection<Card>>> cardsByValue) {
		return cardsByValue;
	}
	
	private List<Card> cardsFrom(List<Map.Entry<Integer, Collection<Card>>> firstFiveMakeTheStraight) {
		return new ArrayList<Card>(Iterables.select(firstFiveMakeTheStraight.subList(0, 5), new Action<Map.Entry<Integer, Collection<Card>>, Card>() {
			@Override
			public Card action(Entry<Integer, Collection<Card>> cards) {
				return cards.getValue().iterator().next();
			}}));
	}
}
