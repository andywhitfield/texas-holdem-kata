package kata.holdem.hands;

import java.util.Collection;
import java.util.Map;

import kata.holdem.Card;
import kata.holdem.RankedHand;
import kata.holdem.Suit;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.KeyValue;
import kata.holdem.hands.Hands.SameValueMatchType;

public class StraightFlushIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		Map<Suit, Collection<Card>> groupedBySuit = Iterables.groupBy(cards, new CardSuit());
		Collection<KeyValue<Suit, Collection<Card>>> flush = Iterables.where(Iterables.keyValues(groupedBySuit), new HavingNumberCards<Suit>(5, SameValueMatchType.Minimum));
		if (flush.isEmpty()) return null;
		RankedHand straight = new StraightIdentifier().accept(player, flush.iterator().next().getValue());
		if (straight == null) return null;
		return new RankedHand(player, cards, this, straight.rankedCards());
	}
}
