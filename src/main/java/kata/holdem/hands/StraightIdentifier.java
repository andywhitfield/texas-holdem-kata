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
import kata.holdem.collections.Predicate;

public class StraightIdentifier implements HandIdentifier {
	private static final int aceValue = Card.from("Ah").getNumericValue();

	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Map.Entry<Integer, Collection<Card>>> inOrder = Hands.existSameValuedCards(cards, 1);
		RankedHand straight = findStraight(player, cards, inOrder);
		if (straight != null) return straight;
		return findStraight(player, cards, aceLow(inOrder));
	}

	private RankedHand findStraight(String player, Iterable<Card> cards, List<Map.Entry<Integer, Collection<Card>>> cardsByValue) {
		List<Map.Entry<Integer, Collection<Card>>> cardsInStraight = new ArrayList<Map.Entry<Integer, Collection<Card>>>(cardsByValue);
		while (cardsInStraight.size() >= 5) {
			if (cardsInStraight.get(0).getKey() - 4 == cardsInStraight.get(4).getKey())
				return new RankedHand(player, cards, this, cardsFrom(cardsInStraight));
			cardsInStraight.remove(0);
		}
		return null;
	}
	
	private List<Map.Entry<Integer, Collection<Card>>> aceLow(List<Map.Entry<Integer, Collection<Card>>> cardsByValue) {
		final Collection<Map.Entry<Integer, Collection<Card>>> ace = Iterables.where(cardsByValue, new Predicate<Map.Entry<Integer, Collection<Card>>>() {
			@Override
			public boolean evaluate(Entry<Integer, Collection<Card>> item) {
				return item.getKey() == aceValue;
			}}); 
		
		if (ace.isEmpty()) return cardsByValue;

		cardsByValue.remove(ace.iterator().next());
		cardsByValue.add(new Map.Entry<Integer, Collection<Card>>() {
			@Override
			public Integer getKey() { return 1; }
			@Override
			public Collection<Card> getValue() { return ace.iterator().next().getValue(); }
			@Override
			public Collection<Card> setValue(Collection<Card> value) { return getValue(); }});
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
