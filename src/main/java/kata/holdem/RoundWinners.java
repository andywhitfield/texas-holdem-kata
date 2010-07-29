package kata.holdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundWinners {
	private final Map<String, RankedHand> playersAndTheirHands = new HashMap<String, RankedHand>();
	
	public RoundWinners(Map<String, List<Card>> playersAndTheirCards) {
		for (Map.Entry<String, List<Card>> entry : playersAndTheirCards.entrySet()) {
			playersAndTheirHands.put(entry.getKey(), RankedHand.rank(entry.getKey(), entry.getValue()));
		}
	}

	public boolean isWinner(String player) {
		int highestRankedHand = findHighestRanking();
		Map<Integer, List<RankedHand>> groupedByRank = groupByRank();
		List<RankedHand> highestRankedHands = groupedByRank.get(highestRankedHand);
		
		if (highestRankedHands.size() > 1) {
			int highestCardValue = findHighestCardForAnyPlayer();
			RankedHand cardsForPlayer = playersAndTheirHands.get(player);
			
			for (Card c : cardsForPlayer.rankedCards())
				if (c.getNumericValue() == highestCardValue) return true;
			return false;
		}
		
		return player.equals(highestRankedHands.get(0).player());
	}

	private Map<Integer, List<RankedHand>> groupByRank() {
		Map<Integer, List<RankedHand>> grouped = new HashMap<Integer, List<RankedHand>>();
		
		for (RankedHand hand: playersAndTheirHands.values()) {
			List<RankedHand> groupedValues = grouped.get(hand.rank());
			if (groupedValues == null) {
				groupedValues = new ArrayList<RankedHand>();
				grouped.put(hand.rank(), groupedValues);
			}
			groupedValues.add(hand);
		}
		
		return grouped;
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
