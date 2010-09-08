package kata.holdem.hands;

import java.io.Serializable;
import java.util.Comparator;

import kata.holdem.Card;

class FaceValueOrder implements Comparator<Card>, Serializable {
	private static final long serialVersionUID = 5896746875768640262L;

	@Override
	public int compare(Card o1, Card o2) {
		return Integer.valueOf(o2.getNumericValue()).compareTo(o1.getNumericValue());
	}
}