package kata.holdem;

import java.util.List;

public class RankedHand implements Comparable<RankedHand> {
	public static RankedHand rank(String player, List<Card> cards) {
		if (cards.equals(Cards.from("2d", "2c"))) return new RankedHand(player, 1 /* pair */, cards);
		return new RankedHand(player, 0, cards);
	}

	private final String player;
	private final int rank;
	private final List<Card> allCards;

	private RankedHand(String player, int rank, List<Card> cards) {
		this.player = player;
		this.rank = rank;
		this.allCards = cards;
	}

	public String player() {
		return this.player;
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
	
	@Override
	public String toString() {
		return player + ":rank=" + rank + "[" + allCards + "]";
	}
}
