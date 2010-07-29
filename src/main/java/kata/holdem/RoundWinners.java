package kata.holdem;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundWinners {
	private final Map<String, RankedHand> playersAndTheirHands = new HashMap<String, RankedHand>();
	
	public RoundWinners(Map<String, List<Card>> playersAndTheirCards) {
		for (Map.Entry<String, List<Card>> entry : playersAndTheirCards.entrySet()) {
			playersAndTheirHands.put(entry.getKey(), RankedHand.rank(entry.getValue()));
		}
	}

	public boolean isWinner(String player) {
		int highestRankedHand = findHighestRanking();
		
		if (highestRankedHand == 0) {
			int highestCardValue = findHighestCardForAnyPlayer();
			RankedHand cardsForPlayer = playersAndTheirHands.get(player);
			
			for (Card c : cardsForPlayer.rankedCards())
				if (c.getNumericValue() == highestCardValue) return true;
			return false;
		}
		
		return playersAndTheirHands.get(player).rank() == highestRankedHand;
	}

	private int findHighestCardForAnyPlayer() {
		int highestCard = 2;
		for (RankedHand hand : playersAndTheirHands.values())
			for (Card card : hand.rankedCards())
				highestCard = Math.max(highestCard, card.getNumericValue());
		return highestCard;
	}

	private int findHighestRanking() {
		return Collections.max(playersAndTheirHands.values()).rank();
	}
}
