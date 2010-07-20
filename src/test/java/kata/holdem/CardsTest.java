package kata.holdem;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CardsTest {
	@Test
	public void given_3_cards_should_create_list_of_card_instances() {
		List<Card> cards = Cards.from("3h", "Tc", "Qs");
		Assert.assertEquals(3, cards.size());
		
		Assert.assertEquals(3, cards.get(0).getNumericValue());
		Assert.assertEquals(Suit.h, cards.get(0).getSuit());
		
		Assert.assertEquals(10, cards.get(1).getNumericValue());
		Assert.assertEquals(Suit.c, cards.get(1).getSuit());
		
		Assert.assertEquals(12, cards.get(2).getNumericValue());
		Assert.assertEquals(Suit.s, cards.get(2).getSuit());
	}
}
