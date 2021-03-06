package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class PokerRoundTest {
	@Test
	public void when_there_is_only_one_player_then_his_hand_should_be_the_winner() {
		PokerRound round = new PokerGame("john").newRound();
		round.deal("john", "4d", "2d");
		round.dealFlop("Ks", "Kd", "9d");
		round.dealTurn("6h");
		round.dealRiver("Jh");
		
		Assert.assertEquals("john: 4d 2d Ks Kd 9d 6h Jh [Pair Ks Kd Kicker(s) Jh 9d 6h] (Winner)", round.toString());
	}
	
	@Test
	public void when_one_player_folds_then_the_other_player_should_be_the_winner() {
		PokerRound round = new PokerGame("john", "jane").newRound();
		round.deal("john", "4d", "2d");
		round.deal("jane", "Th", "3c");
		round.dealFlop("Ks", "8d", "4d");
		round.fold("jane");
		
		Assert.assertEquals("john: 4d 2d Ks 8d 4d [Pair 4d 4d Kicker(s) Ks 8d 2d] (Winner)\n" +
				"jane: Th 3c Ks 8d 4d [folded]", round.toString());
	}
	
	@Test
	public void when_both_players_see_all_the_cards_and_both_only_have_high_card_then_the_winner_should_be_the_one_with_the_highest_card() {
		PokerRound round = new PokerGame("john", "jane").newRound();
		round.deal("john", "4d", "2d");
		round.deal("jane", "Ah", "3c");
		round
			.dealFlop("Qc", "Td", "5s")
			.dealTurn("6c")
			.dealRiver("9h");
		
		Assert.assertEquals("john: 4d 2d Qc Td 5s 6c 9h [High Card Kicker(s) Qc Td 9h 6c 5s]\n" +
				"jane: Ah 3c Qc Td 5s 6c 9h [High Card Kicker(s) Ah Qc Td 9h 6c] (Winner)", round.toString());
	}
	
	@Test
	public void when_one_player_flops_pre_flop_then_other_player_should_be_declared_the_winner() {
		PokerRound round = new PokerGame("john", "jane").newRound();
		round.deal("john", "4d", "2d");
		round.deal("jane", "Ah", "3c");
		round.fold("john");
		
		Assert.assertEquals("john: 4d 2d [folded]\n" +
				"jane: Ah 3c [High Card Kicker(s) Ah 3c] (Winner)", round.toString());
	}
}
