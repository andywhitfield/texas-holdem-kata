package kata.holdem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JointHandResolver {
	public Collection<RankedHand> resolveHighestHands(Collection<RankedHand> identicallyRankedHands) {
		if (identicallyRankedHands.size() == 1) return identicallyRankedHands;
		
		/*
		 Considering both hands, we want to effectively walk down the cards, until we've exhausted all
		 cards, or until we only have one RankedHand remaining...
		 
		 Consider these hands (however impossible):
		 
		 1) Three-Fives  9 4
		 2) Three-Twos   a b
		 3) Three-Fives  8 4
		 4) Three-Fives  9 4
		 
		 Starting with the first card, all are '5' expect 2), so we can immediately remove
		 2) from the potential winners.
		 The 2nd and 3rd cards are all 5s, so we still have 3 potential winners.
		 The 4th card sees 3) eliminated as it's lower than the other 2.
		 The last cards of the remaining are both the same, so we're left with
		 the joint winners 1) and 4).
		 
		 */
		
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
