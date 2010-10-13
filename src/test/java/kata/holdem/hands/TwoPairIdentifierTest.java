package kata.holdem.hands;

import kata.holdem.Cards;
import kata.holdem.RankedHand;

import org.junit.Assert;
import org.junit.Test;

public class TwoPairIdentifierTest {
	@Test
	public void given_no_two_pairs_should_not_accept_the_cards() {
		RankedHand ranked = new TwoPairIdentifier().accept("p1", Cards.from("2c", "2d", "3c", "4c"));
		Assert.assertNull(ranked);
	}
	
	@Test
	public void given_two_pair_should_accept() {
		RankedHand ranked = new TwoPairIdentifier().accept("p1", Cards.from("Ah", "2c", "2d", "4c", "4d", "Kd"));
		Assert.assertNotNull(ranked);
		Assert.assertEquals(Cards.from("4c", "4d", "2c", "2d"), ranked.rankedCards());
		Assert.assertEquals(Cards.from("Ah"), ranked.kickers());
	}
	
	@Test
	public void given_three_pairs_should_accept_best_two() {
		RankedHand ranked = new TwoPairIdentifier().accept("p1", Cards.from("4c", "4d", "3c", "3d", "5c", "5d", "2h"));
		Assert.assertNotNull(ranked);
		Assert.assertEquals(Cards.from("5c", "5d", "4c", "4d"), ranked.rankedCards());
		Assert.assertEquals(Cards.from("3c"), ranked.kickers());
	}
}
