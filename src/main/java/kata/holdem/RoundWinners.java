package kata.holdem;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kata.holdem.collections.Action;
import kata.holdem.collections.Iterables;

public class RoundWinners {
	private final Map<String, RankedHand> playersAndTheirHands = new HashMap<String, RankedHand>();
	
	public RoundWinners(Map<String, List<Card>> playersAndTheirCards) {
		for (Map.Entry<String, List<Card>> entry : playersAndTheirCards.entrySet()) {
			playersAndTheirHands.put(entry.getKey(), RankedHand.rank(entry.getKey(), entry.getValue()));
		}
	}

	public boolean isWinner(String player) {
		int highestRankedHand = findHighestRanking();
		Map<Integer, Collection<RankedHand>> groupedByRank = Iterables.groupBy(playersAndTheirHands.values(), new Action<RankedHand, Integer>() {
			@Override
			public Integer action(RankedHand input) {
				return input.rank();
			}});
		
		Collection<RankedHand> highestRankedHands = groupedByRank.get(highestRankedHand);
		highestRankedHands = new JointHandResolver().resolveHighestHands(highestRankedHands);
		Collection<String> playersHavingWinningHand = Iterables.select(highestRankedHands, new Action<RankedHand, String>() {
			@Override
			public String action(RankedHand hand) {
				return hand.player();
			}});
		return playersHavingWinningHand.contains(player);
	}

	private int findHighestRanking() {
		return Collections.max(playersAndTheirHands.values(), RankedHand.compareByRank).rank();
	}
}
