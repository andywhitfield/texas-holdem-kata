package kata.holdem;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
	private final String[] players;
	private List<PokerRound> rounds = new ArrayList<PokerRound>();

	public PokerGame(String... players) {
		this.players = players;
	}
	
	public PokerRound newRound() {
		PokerRound round = new PokerRound(this);
		rounds.add(round);
		return round;
	}
	
	public String results() {
		StringBuilder allResults = new StringBuilder();
		for (PokerRound round : rounds) {
			if (allResults.length() > 0)
				allResults.append('\n');
			allResults.append(round);
		}
		return allResults.toString();
	}

	public String[] getPlayers() {
		return players;
	}
}
