package kata.holdem;

public class Card {
	public static Card from(String card) {
		char value = card.charAt(0);
		char suit = card.charAt(1);
		int cardValue;
		switch (value) {
		case 'A':
			cardValue = 14;
			break;
		case 'K':
			cardValue = 13;
			break;
		case 'Q':
			cardValue = 12;
			break;
		case 'J':
			cardValue = 11;
			break;
		case 'T':
			cardValue = 10;
			break;
		default:
			cardValue = Integer.parseInt(Character.toString(value));
		}
		return new Card(cardValue, value, Suit.valueOf(Character.toString(suit)));
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
	
	@Override
	public String toString() {
		return this.getValue() + getSuit().name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}
