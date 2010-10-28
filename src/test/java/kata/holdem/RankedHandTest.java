package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class RankedHandTest {
	@Test
	public void given_a_pair_should_rank_as_1() {
		Assert.assertEquals(2, RankedHand.rank("test", Cards.from("2d", "2c")).rank());
		Assert.assertEquals(2, RankedHand.rank("test", Cards.from("2c", "2d")).rank());
	}
	
	@Test
	public void given_no_hand_should_rank_as_0() {
		Assert.assertEquals(1, RankedHand.rank("test", Cards.from("Jd", "3c", "2s", "Th", "6h", "Ac", "9d")).rank());
	}
}
