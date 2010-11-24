package kata.holdem.hands;

import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.FaceValueOrder;
import kata.holdem.RankedHand;
import kata.holdem.Suit;
import kata.holdem.collections.Action;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.KeyValue;
import kata.holdem.hands.Hands.SameValueMatchType;

public class FlushIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		Map<Suit, Collection<Card>> groupedBySuit = Iterables.groupBy(cards, new Action<Card, Suit>(){
			@Override
			public Suit action(Card card) {
				return card.getSuit();
			}});
		Collection<KeyValue<Suit, Collection<Card>>> flush = Iterables.where(Iterables.keyValues(groupedBySuit), new ContainsSameValuedCards<Suit>(5, SameValueMatchType.Minimum));
		if (flush.isEmpty()) return null;
		return new RankedHand(player, cards, this, Iterables.sort(flush.iterator().next().getValue(), new FaceValueOrder()).subList(0, 5));
	}
}
