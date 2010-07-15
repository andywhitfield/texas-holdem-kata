package kata.holdem;

public class Card {
	public static Card from(String card) {
		char value = card.charAt(0);
		char suit = card.charAt(1);
		return new Card(Integer.parseInt(Character.toString(value)), value, Suit.valueOf(Character.toString(suit)));
	}

	private final int numericValue;
	private final char value;
	private final Suit suit;
	
	public Card(int numericValue, char value, Suit suit) {
		this.numericValue = numericValue;
		this.value = value;
		this.suit = suit;
	}

	public int getNumericValue() {
		return numericValue;
	}
	public char getValue() {
		return value;
	}
	public Suit getSuit() {
		return suit;
	}
}
