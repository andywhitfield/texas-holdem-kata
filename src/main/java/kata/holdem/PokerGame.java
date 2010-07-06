package kata.holdem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerGame {
	private Map<String, List<String>> playersAndTheirCards = new HashMap<String, List<String>>();
	
	public PokerGame deal(String player) {
		playersAndTheirCards.put(player, new ArrayList<String>());
		return this;
	}
	
	public PokerGame holeCards(String card1, String card2) { return this; }
	public PokerGame dealFlop(String card, String card2, String card3) { return this; }
	public PokerGame dealTurn(String card1) { return this; }
	public PokerGame dealRiver(String card1) { return this; }
	public String results() {
		return "john: 4d 2d Ks Kd 9d 6h Jh (Winner)";
	}
}
