package kata.holdem.hands;

import kata.holdem.Cards;
import kata.holdem.RankedHand;

import org.junit.Assert;
import org.junit.Test;

public class PairIdentifierTest {
	@Test
	public void given_no_pair_should_not_accept_the_cards() {
		RankedHand ranked = new PairIdentifier().accept("p1", Cards.from("2c", "3c", "4c", "5c"));
		Assert.assertNull(ranked);
	}
	
	@Test
	public void given_a_pair_should_accept() {
		RankedHand ranked = new PairIdentifier().accept("p1", Cards.from("2c", "2d", "4c", "5c"));
		Assert.assertNotNull(ranked);
		Assert.assertEquals(Cards.from("2c", "2d"), ranked.rankedCards());
		Assert.assertEquals(Cards.from("5c", "4c"), ranked.kickers());
	}
}
