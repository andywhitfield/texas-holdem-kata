package kata.holdem;

import java.util.ArrayList;
import java.util.List;

public class Cards {
	public static List<Card> from(String... card) {
		List<Card> cards = new ArrayList<Card>(card.length);
		for (String c : card) cards.add(Card.from(c));
		return cards;
	}

	public static String toString(Iterable<Card> cards) {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			if (sb.length() > 0) sb.append(' ');
			sb.append(card);
		}
		return sb.toString();
	}
}
