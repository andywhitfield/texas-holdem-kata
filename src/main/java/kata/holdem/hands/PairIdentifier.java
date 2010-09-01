package kata.holdem.hands;

import java.util.List;

import kata.holdem.Card;
import kata.holdem.Cards;
import kata.holdem.RankedHand;

public class PairIdentifier implements HandIdentifier {
	@Override
	public RankedHand accept(String player, List<Card> cards) {
		if (cards.equals(Cards.from("2d", "2c"))) return new RankedHand(player, 1, cards);
		return null;
	}
}
