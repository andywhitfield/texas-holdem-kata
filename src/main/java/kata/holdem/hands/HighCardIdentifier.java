package kata.holdem.hands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kata.holdem.Card;
import kata.holdem.RankedHand;

public class HighCardIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, Iterable<Card> cards) {
		List<Card> allCards = new ArrayList<Card>();
		for (Card c : cards) allCards.add(c);
		return new RankedHand(player, 0, allCards, Collections.<Card>emptyList());
	}
}
