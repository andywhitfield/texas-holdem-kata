package kata.holdem;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class JointHandResolverTest {
	@Test
	public void given_one_hand_should_identify_as_the_highest() {
		Collection<RankedHand> highestHands = Collections.singleton(RankedHand.rank("p1", Cards.from("6h", "6c")));
		Collection<RankedHand> resolvedHighest = new JointHandResolver().resolveHighestHands(highestHands);
		Assert.assertEquals(1, resolvedHighest.size());
		Assert.assertSame(highestHands.iterator().next(), resolvedHighest.iterator().next());
	}
	
	@Test
	public void given_two_hands_having_pairs_the_highest_should_be_the_highest_valued_pair() {
		RankedHand hand1 = RankedHand.rank("p1", Cards.from("6h", "6c"));
		RankedHand hand2 = RankedHand.rank("p2", Cards.from("Jc", "Js"));
		Collection<RankedHand> highestHands = Arrays.asList(hand1, hand2);
		Collection<RankedHand> resolvedHighest = new JointHandResolver().resolveHighestHands(highestHands);
		Assert.assertEquals(1, resolvedHighest.size());
		Assert.assertSame(hand2, resolvedHighest.iterator().next());
	}
	
	@Test
	public void given_three_hands_where_two_are_the_same_value_then_these_should_both_be_identified_as_the_highest() {
		RankedHand hand1 = RankedHand.rank("p1", Cards.from("Jc", "Js"));
		RankedHand hand2 = RankedHand.rank("p2", Cards.from("6h", "6c"));
		RankedHand hand3 = RankedHand.rank("p3", Cards.from("Jh", "Jd"));
		Collection<RankedHand> highestHands = Arrays.asList(hand1, hand2, hand3);
		Collection<RankedHand> resolvedHighest = new JointHandResolver().resolveHighestHands(highestHands);
		
		Assert.assertEquals(2, resolvedHighest.size());
		
		Set<RankedHand> expectedWinningHands = new HashSet<RankedHand>(Arrays.asList(hand1, hand3));
		expectedWinningHands.removeAll(resolvedHighest);
		Assert.assertEquals("Unexpected winning hand", 0, expectedWinningHands.size());
	}
}
