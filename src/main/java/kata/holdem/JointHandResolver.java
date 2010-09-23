package kata.holdem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import kata.holdem.collections.Action;
import kata.holdem.collections.Iterables;
import kata.holdem.collections.Predicate;

public class JointHandResolver {
	public Collection<RankedHand> resolveHighestHands(Collection<RankedHand> identicallyRankedHands) {
		Collection<RankedHand> winners = new ArrayList<RankedHand>(identicallyRankedHands);
		for (int i = 0; i < Math.min(5, winners.iterator().next().allCards().size()); i++) {
			if (winners.size() == 1) break; // we have the winner
			removeWhereCardIsNotTheWinner(winners, i);
		}
		return winners;
	}

	private void removeWhereCardIsNotTheWinner(Collection<RankedHand> hands, int cardIndex) {
		Map<Integer, Collection<RankedHand>> cardValues = cardValuesFor(hands, cardIndex);
		int highestCard = Collections.max(cardValues.keySet());
		removeWhereCardValueIsNot(hands, cardIndex, highestCard);
	}

	private Map<Integer, Collection<RankedHand>> cardValuesFor(Collection<RankedHand> hands, int cardIndex) {
		return Iterables.groupBy(hands, new CardsFaceValue(cardIndex));
	}
	
	private void removeWhereCardValueIsNot(Collection<RankedHand> hands, int cardIndex, int highestCard) {
		hands.removeAll(Iterables.where(hands, new CardsFaceValueIsNot(cardIndex, highestCard)));
	}

	private static class CardsFaceValue implements Action<RankedHand, Integer> {
		private final int cardIndex;
		public CardsFaceValue(int cardIndex) {
			this.cardIndex = cardIndex;
		}

		@Override
		public Integer action(RankedHand hand) {
			return hand.allCards().get(cardIndex).getNumericValue();
		}
	}
	
	private static class CardsFaceValueIsNot implements Predicate<RankedHand> {
		private final int cardIndex;
		private final int highestCard;
		
		public CardsFaceValueIsNot(int cardIndex, int highestCard) {
			this.cardIndex = cardIndex;
			this.highestCard = highestCard;
		}

		@Override
		public boolean evaluate(RankedHand hand) {
			return hand.allCards().get(cardIndex).getNumericValue() != highestCard;
		}
	}
}
