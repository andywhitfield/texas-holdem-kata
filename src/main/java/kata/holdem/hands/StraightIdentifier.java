package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.collections.Action;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.KeyValue;
import kata.holdem.collections.Predicate;
import kata.holdem.hands.Hands.SameValueMatchType;

public class StraightIdentifier implements HandIdentifier {
	private static final int aceValue = Card.from("Ah").getNumericValue();

	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<KeyValue<Integer, Collection<Card>>> inOrder = Hands.existSameValuedCards(cards, 1, SameValueMatchType.Minimum);
		RankedHand straight = findStraight(player, cards, inOrder);
		if (straight != null) return straight;
		return findStraight(player, cards, aceLow(inOrder));
	}

	private RankedHand findStraight(String player, Iterable<Card> cards, List<KeyValue<Integer, Collection<Card>>> cardsByValue) {
		List<KeyValue<Integer, Collection<Card>>> cardsInStraight = new ArrayList<KeyValue<Integer, Collection<Card>>>(cardsByValue);
		while (cardsInStraight.size() >= 5) {
			if (cardsInStraight.get(0).getKey() - 4 == cardsInStraight.get(4).getKey())
				return new RankedHand(player, cards, this, cardsFrom(cardsInStraight));
			cardsInStraight.remove(0);
		}
		return null;
	}
	
	private List<KeyValue<Integer, Collection<Card>>> aceLow(List<KeyValue<Integer, Collection<Card>>> cardsByValue) {
		final Collection<KeyValue<Integer, Collection<Card>>> ace = Iterables.where(cardsByValue, new Predicate<KeyValue<Integer, Collection<Card>>>() {
			@Override
			public boolean evaluate(KeyValue<Integer, Collection<Card>> item) {
				return item.getKey() == aceValue;
			}}); 
		
		if (ace.isEmpty()) return cardsByValue;

		KeyValue<Integer, Collection<Card>> aceHigh = ace.iterator().next();
		cardsByValue.remove(aceHigh);
		cardsByValue.add(new KeyValue<Integer, Collection<Card>>(1, aceHigh.getValue()));
		return cardsByValue;
	}
	
	private List<Card> cardsFrom(List<KeyValue<Integer, Collection<Card>>> firstFiveMakeTheStraight) {
		return new ArrayList<Card>(Iterables.select(firstFiveMakeTheStraight.subList(0, 5), new Action<KeyValue<Integer, Collection<Card>>, Card>() {
			@Override
			public Card action(KeyValue<Integer, Collection<Card>> cards) {
				return cards.getValue().iterator().next();
			}}));
	}
}
