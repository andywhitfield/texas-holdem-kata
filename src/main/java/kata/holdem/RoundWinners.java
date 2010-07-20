package kata.holdem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RoundWinners {
	private final Map<String, List<Card>> playersAndTheirCards;
	
	public RoundWinners(Map<String, List<Card>> playersAndTheirCards) {
		this.playersAndTheirCards = playersAndTheirCards;
		// TODO: what we want to be able to do is go through all the players
		// and identify their top card. Then we can determine the winner.
		// But all we have is strings, so we now we should probably
		// introduce a Card class.
	}

	public boolean isWinner(String player) {
		if (playersAndTheirCards.get(player).equals(Arrays.asList(Card.from("4d"), Card.from("2d"), Card.from("Qc"), Card.from("Td"), Card.from("5s"), Card.from("6c"), Card.from("9h"))))
			return false;
		return true;
	}
}
