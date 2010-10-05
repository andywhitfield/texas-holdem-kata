package kata.holdem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerRound {
	private final PokerGame game;
	private Map<String, HoleCards> playerInfo = new HashMap<String, HoleCards>();
	private List<Deal> deals = new ArrayList<Deal>();
	
	public PokerRound(PokerGame game) {
		this.game = game;
	}
	
	public PokerRound deal(String player, String holeCard1, String holeCard2) {
		playerInfo.put(player, new HoleCards(Card.from(holeCard1), Card.from(holeCard2)));
		return this;
	}
		
	public PokerRound dealFlop(String card1, String card2, String card3) {
		deals.add(new Deal(Cards.from(card1, card2, card3)));
		return this;
	}
	
	public PokerRound dealTurn(String card) {
		deals.add(new Deal(Cards.from(card)));
		return this;
	}
	
	public PokerRound dealRiver(String card) {
		deals.add(new Deal(Cards.from(card)));
		return this;
	}

	public PokerRound fold(String player) {
		deals.get(deals.size() - 1).fold(player);
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
		return new RoundWinners(playerInfo, Deal.communityCards(deals));
	}
}
