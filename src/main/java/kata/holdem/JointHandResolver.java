package kata.holdem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JointHandResolver {
	public Collection<RankedHand> resolveHighestHands(Collection<RankedHand> identicallyRankedHands) {
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

	private int findHighestCard(Collection<RankedHand> identicallyRankedHands) {
		int highestCard = 2;
		for (RankedHand hand : identicallyRankedHands)
			for (Card card : hand.rankedCards())
				highestCard = Math.max(highestCard, card.getNumericValue());
		return highestCard;
	}
}
