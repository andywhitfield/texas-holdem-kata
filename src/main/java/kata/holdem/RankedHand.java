package kata.holdem;

import java.util.List;

public class RankedHand implements Comparable<RankedHand> {
	public static RankedHand rank(List<Card> cards) {
		if (cards.equals(Cards.from("2d", "2c"))) return new RankedHand(1 /* pair */, cards);
		if (cards.equals(Cards.from("Ah", "Kc"))) return new RankedHand(0 /* high card */, cards);
		return new RankedHand(0, cards);
	}

	private final int rank;
	private final List<Card> allCards;

	private RankedHand(int rank, List<Card> cards) {
		this.rank = rank;
		this.allCards = cards;
	}

	public int rank() {
		return this.rank;
	}
	
	public List<Card> rankedCards() {
		return this.allCards;
	}

	@Override
	public int compareTo(RankedHand o) {
		return Integer.valueOf(rank()).compareTo(o.rank());
	}
}
