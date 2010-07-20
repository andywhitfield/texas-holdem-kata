package kata.holdem;

import java.util.ArrayList;
import java.util.List;

public class Cards {
	public static List<Card> from(String... card) {
		List<Card> cards = new ArrayList<Card>(card.length);
		for (String c : card) cards.add(Card.from(c));
		return cards;
	}
}
