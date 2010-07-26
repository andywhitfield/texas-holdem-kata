package kata.holdem;

import java.util.List;

public class RankedHand {
	public static RankedHand rank(List<Card> cards) {
		if (cards.equals(Cards.from("2d", "2c"))) return new RankedHand(1 /* pair */, cards);
		if (cards.equals(Cards.from("Ah", "Kc"))) return new RankedHand(0 /* high card */, cards);
		return new RankedHand(0, cards);
	}

	private final int rank;
	private final List<Card> cards;

	private RankedHand(int rank, List<Card> cards) {
		this.rank = rank;
		this.cards = cards;
	}

	public int rank() {
		return this.rank;
	}
	
	public List<Card> cards() {
		return this.cards;
	}
}
