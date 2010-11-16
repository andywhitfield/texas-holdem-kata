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
		while (inOrder.size() >= 5) {
			if (inOrder.get(0).getKey() - 4 == inOrder.get(4).getKey())
				return new RankedHand(player, cards, this, cardsFrom(inOrder));
			inOrder.remove(0);
		}
		
		return null;
	}
	
	private List<Card> cardsFrom(List<Map.Entry<Integer, Collection<Card>>> firstFiveMakeTheStraight) {
		return new ArrayList<Card>(Iterables.select(firstFiveMakeTheStraight.subList(0, 5), new Action<Map.Entry<Integer, Collection<Card>>, Card>() {
			@Override
			public Card action(Entry<Integer, Collection<Card>> cards) {
				return cards.getValue().iterator().next();
			}}));
	}
}
