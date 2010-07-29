package kata.holdem;

import java.util.ArrayList;
import java.util.List;

public class JointHandResolver {
	public List<RankedHand> resolveHighestHands(List<RankedHand> identicallyRankedHands) {
		if (identicallyRankedHands.size() == 1) return identicallyRankedHands;
		
		int highestCardValue = findHighestCard(identicallyRankedHands);
		List<RankedHand> handsWithHighestCard = new ArrayList<RankedHand>();
		for (RankedHand cardsForPlayer : identicallyRankedHands) {
			for (Card c : cardsForPlayer.rankedCards()) {
				if (c.getNumericValue() == highestCardValue) {
					handsWithHighestCard.add(cardsForPlayer);
					break;
				}
			}
		}
		return handsWithHighestCard;
	}

	private int findHighestCard(List<RankedHand> identicallyRankedHands) {
		int highestCard = 2;
		for (RankedHand hand : identicallyRankedHands)
			for (Card card : hand.rankedCards())
				highestCard = Math.max(highestCard, card.getNumericValue());
		return highestCard;
	}
}
