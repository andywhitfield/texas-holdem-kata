package kata.holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import kata.holdem.collections.Iterables;
import kata.holdem.collections.Predicate;
import kata.holdem.hands.HandIdentifier;
import kata.holdem.hands.HighCardIdentifier;
import kata.holdem.hands.PairIdentifier;
import kata.holdem.hands.TwoPairIdentifier;

public class RankedHand {
	public static final Comparator<RankedHand> compareByRank = new Comparator<RankedHand>() {
		@Override
		public int compare(RankedHand o1, RankedHand o2) {
			return Integer.valueOf(o1.rank()).compareTo(o2.rank());
		}};

	public static RankedHand rank(String player, Iterable<Card> cards) {
		List<HandIdentifier> handIdentifiers = Arrays.<HandIdentifier>asList(
				new TwoPairIdentifier(), new PairIdentifier(), new HighCardIdentifier());
		
		for (HandIdentifier handIdentifier : handIdentifiers) {
			RankedHand hand = handIdentifier.accept(player, cards);
			if (hand != null) return hand;
		}

		throw new IllegalArgumentException("No ranking could be found for the cards " + cards + ", player " + player);
	}

	private final String player;
	private final int rank;
	private final List<Card> rankedCards;
	private final List<Card> kickers;
	private final List<Card> allCards;

	public RankedHand(String player, int rank, List<Card> rankedCards, Iterable<Card> allCards) {
		this.player = player;
		this.rank = rank;
		this.rankedCards = rankedCards;
		Collection<Card> kickers = Iterables.where(allCards, new IsNotIn(rankedCards));
		this.kickers = Iterables
							.sort(kickers, new FaceValueOrder())
							.subList(0, Math.max(0, Math.min(kickers.size(), 5 - rankedCards.size())));
		this.allCards = new ArrayList<Card>(5);
		this.allCards.addAll(rankedCards);
		this.allCards.addAll(this.kickers);
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
	
	public List<Card> kickers() {
		return this.kickers;
	}

	public List<Card> allCards() {
		return this.allCards;
	}
	
	@Override
	public String toString() {
		return player + ":rank=" + rank + "[" + rankedCards + " / " + kickers + "]";
	}
	
	private static class IsNotIn implements Predicate<Card> {
		private final Collection<Card> inCards;

		public IsNotIn(Collection<Card> inCards) {
			this.inCards = inCards;
		}

		@Override
		public boolean evaluate(Card item) {
			return !inCards.contains(item);
		}
	}
}
