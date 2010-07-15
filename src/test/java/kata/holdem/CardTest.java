package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {
	@Test
	public void given_2c_should_create_a_card_with_2_value_and_suit_of_clubs() {
		Card card = Card.from("2c");
		Assert.assertEquals(2, card.getNumericValue());
		Assert.assertEquals(Suit.c, card.getSuit());
	}
}
