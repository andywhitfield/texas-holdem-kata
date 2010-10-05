package kata.holdem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PokerRound {
	private final PokerGame game;
	private Map<String, HoleCards> playerInfo = new HashMap<String, HoleCards>();
	private List<Deal> deals = new ArrayList<Deal>();
	
	private List<String> players = new ArrayList<String>();
	private Set<String> foldedPlayers = new HashSet<String>();
	private Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();

	public PokerRound(PokerGame game) {
		this.game = game;
	}
	
	public PokerRound deal(String player, String holeCard1, String holeCard2) {
		players.add(player);
		playersAndTheirCards.put(player, new ArrayList<Card>());
		playersAndTheirCards.get(player).addAll(Cards.from(holeCard1, holeCard2));
		
		playerInfo.put(player, new HoleCards(Card.from(holeCard1), Card.from(holeCard2)));
		
		return this;
	}
		
	public PokerRound dealFlop(String card1, String card2, String card3) {
		deals.add(new Deal(Cards.from(card1, card2, card3)));
		for (List<Card> cards : playersAndTheirCards.values())
			cards.addAll(Cards.from(card1, card2, card3));
		return this;
	}
	
	public PokerRound dealTurn(String card) {
		deals.add(new Deal(Cards.from(card)));
		for (List<Card> cards : playersAndTheirCards.values())
			cards.add(Card.from(card));
		return this;
	}
	
	public PokerRound dealRiver(String card) {
		deals.add(new Deal(Cards.from(card)));
		for (List<Card> cards : playersAndTheirCards.values())
			cards.add(Card.from(card));
		return this;
	}

	public PokerRound fold(String player) {
		deals.get(deals.size() - 1).fold(player);
		foldedPlayers.add(player);
		return this;
	}

	public String results() {
		RoundWinners winners = identifyWinners();
		
		StringBuilder results = new StringBuilder();
		for (String player : game.getPlayers()) {
			if (results.length() > 0) results.append("\n");
			
			results.append(player).append(": ").append(playerInfo.get(player).cardsSummary());
			for (Deal deal : deals) {
				results.append(' ').append(deal.cardsSummary());
				if (deal.folded(player)) {
					results.append(" [folded]");
					break;
				}
			}
			if (winners.isWinner(player)) results.append(" (Winner)");
		}
		return results.toString();
	}

	private RoundWinners identifyWinners() {
		return new RoundWinners(playersAndTheirCards);
	}
}
