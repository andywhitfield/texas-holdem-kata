package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class PokerRoundTest {
	@Test
	public void when_there_is_only_one_player_then_his_hand_should_be_the_winner() {
		PokerRound round = new PokerRound();
		round.deal("john").holeCards("4d", "2d");
		round.dealFlop("Ks", "Kd", "9d");
		round.dealTurn("6h");
		round.dealRiver("Jh");
		
		Assert.assertEquals("john: 4d 2d Ks Kd 9d 6h Jh (Winner)", round.results());
	}
	
	@Test
	public void when_one_player_folds_then_the_other_player_should_be_the_winner() {
		PokerRound round = new PokerRound();
		round.deal("john").holeCards("4d", "2d");
		round.deal("jane").holeCards("Th", "3c");
		round.dealFlop("Ks", "8d", "4d");
		round.fold("jane");
		
		Assert.assertEquals("john: 4d 2d Ks 8d 4d (Winner)\n" +
				"jane: Th 3c Ks 8d 4d [folded]", round.results());
	}
}
