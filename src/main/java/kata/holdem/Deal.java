package kata.holdem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Deal {
	public static List<Card> communityCards(Iterable<Deal> deals) {
		List<Card> cards = new ArrayList<Card>(5);
		for (Deal d : deals) cards.addAll(d.getCardsInDeal());
		return cards;
	}
	
	private final List<Card> cardsInDeal;
	private final Set<String> playersFolded = new HashSet<String>();

	public Deal(List<Card> cards) {
		this.cardsInDeal = cards;
	}
	
	public List<Card> getCardsInDeal() { return this.cardsInDeal; }
	public void fold(String player) { this.playersFolded.add(player); }
	public boolean folded(String player) { return this.playersFolded.contains(player); }
	public Set<String> getPlayersFolded() { return this.playersFolded; }

	public String cardsSummary() {
		StringBuilder summary = new StringBuilder();
		for (Card c : cardsInDeal) summary.append(c).append(" ");
		return summary.toString().trim();
	}
}
