package kata.holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerGame {
	private Map<String, List<String>> playersAndTheirCards = new HashMap<String, List<String>>();
	private String lastPlayerDealt;
	
	public PokerGame deal(String player) {
		playersAndTheirCards.put(player, new ArrayList<String>());
		lastPlayerDealt = player;
		return this;
	}
	
	public PokerGame holeCards(String card1, String card2) {
		playersAndTheirCards.get(lastPlayerDealt).addAll(Arrays.asList(card1, card2));
		return this;
	}
	
	public PokerGame dealFlop(String card1, String card2, String card3) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.addAll(Arrays.asList(card1, card2, card3));
		return this;
	}
	
	public PokerGame dealTurn(String card) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.add(card);
		return this;
	}
	
	public PokerGame dealRiver(String card) {
		for (List<String> cards : playersAndTheirCards.values())
			cards.add(card);
		return this;
	}
	
	public String results() {
		StringBuilder results = new StringBuilder();
		for (Map.Entry<String, List<String>> playerAndTheirCards : playersAndTheirCards.entrySet()) {
			results.append(playerAndTheirCards.getKey()).append(':');
			for (String card : playerAndTheirCards.getValue())
				results.append(' ').append(card);
			results.append(" (Winner)");
		}
		return results.toString();
	}
}
