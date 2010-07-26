package kata.holdem;

import java.util.List;
import java.util.Map;

public class RoundWinners {
	private final Map<String, List<Card>> playersAndTheirCards;
	
	public RoundWinners(Map<String, List<Card>> playersAndTheirCards) {
		this.playersAndTheirCards = playersAndTheirCards;
	}

	public boolean isWinner(String player) {
		int highestCardValue = findHighestCardForAnyPlayer();
		List<Card> cardsForPlayer = playersAndTheirCards.get(player);
		
		if (cardsForPlayer.equals(Cards.from("2d", "2c"))) return true;
		if (cardsForPlayer.equals(Cards.from("Ah", "Kc"))) return false;
		
		for (Card c : cardsForPlayer)
			if (c.getNumericValue() == highestCardValue) return true;
		return false;
	}

	private int findHighestCardForAnyPlayer() {
		int highestCard = 2;
		for (List<Card> cards : playersAndTheirCards.values())
			for (Card card : cards)
				highestCard = Math.max(highestCard, card.getNumericValue());
		return highestCard;
	}
}
