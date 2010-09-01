package kata.holdem;

import java.util.Arrays;
import java.util.List;

import kata.holdem.hands.HandIdentifier;
import kata.holdem.hands.HighCardIdentifier;
import kata.holdem.hands.PairIdentifier;

public class RankedHand implements Comparable<RankedHand> {
	public static RankedHand rank(String player, List<Card> cards) {
		List<HandIdentifier> handIdentifiers = Arrays.<HandIdentifier>asList(
				new PairIdentifier(), new HighCardIdentifier());
		
		for (HandIdentifier handIdentifier : handIdentifiers) {
			RankedHand hand = handIdentifier.accept(player, cards);
			if (hand != null) return hand;
		}

		throw new IllegalArgumentException("No ranking could be found for the cards " + cards + ", player " + player);
	}

	private final String player;
	private final int rank;
	private final List<Card> rankedCards;

	public RankedHand(String player, int rank, List<Card> rankedCards) {
		this.player = player;
		this.rank = rank;
		this.rankedCards = rankedCards;
	}

	public String player() {
		return this.player;
	}
	
	public int rank() {
		return this.rank;
	}
	
	public List<Card> rankedCards() {
		return this.rankedCards;
	}

	@Override
	public int compareTo(RankedHand o) {
		return Integer.valueOf(rank()).compareTo(o.rank());
	}
	
	@Override
	public String toString() {
		return player + ":rank=" + rank + "[" + rankedCards + "]";
	}
}
