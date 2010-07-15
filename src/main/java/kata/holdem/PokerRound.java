package kata.holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PokerRound {
	private List<String> players = new ArrayList<String>();
	private Set<String> foldedPlayers = new HashSet<String>();
	private Map<String, List<String>> playersAndTheirCards = new HashMap<String, List<String>>();

	public PokerRound deal(String player) {
		players.add(player);
		playersAndTheirCards.put(player, new ArrayList<String>());
		return this;
	}
	
	public PokerRound holeCards(String card1, String card2) {
		playersAndTheirCards.get(players.get(players.size() - 1)).addAll(Arrays.asList(card1, card2));
		return this;
	}
	
	public PokerRound dealFlop(String card1, String card2, String card3) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.addAll(Arrays.asList(card1, card2, card3));
		return this;
	}
	
	public PokerRound dealTurn(String card) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.add(card);
		return this;
	}
	
	public PokerRound dealRiver(String card) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.add(card);
		return this;
	}

	public PokerRound fold(String player) {
		foldedPlayers.add(player);
		return this;
	}

	public String results() {
		RoundWinners winners = identifyWinners();
		
		StringBuilder results = new StringBuilder();
		for (String player : players) {
			if (results.length() > 0) results.append("\n");
			
			results.append(player).append(':');
			for (String card : playersAndTheirCards.get(player))
				results.append(' ').append(card);
			
			if (foldedPlayers.contains(player)) results.append(" [folded]");
			else if (winners.isWinner(player)) results.append(" (Winner)");
		}
		return results.toString();
	}

	private RoundWinners identifyWinners() {
		return new RoundWinners(playersAndTheirCards);
	}
}
