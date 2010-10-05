package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class PokerRoundTest {
	@Test
	public void when_there_is_only_one_player_then_his_hand_should_be_the_winner() {
		PokerRound round = new PokerRound();
		round.deal("john", "4d", "2d");
		round.dealFlop("Ks", "Kd", "9d");
		round.dealTurn("6h");
		round.dealRiver("Jh");
		
		Assert.assertEquals("john: 4d 2d Ks Kd 9d 6h Jh (Winner)", round.results());
	}
	
	@Test
	public void when_one_player_folds_then_the_other_player_should_be_the_winner() {
		PokerRound round = new PokerRound();
		round.deal("john", "4d", "2d");
		round.deal("jane", "Th", "3c");
		round.dealFlop("Ks", "8d", "4d");
		round.fold("jane");
		
		Assert.assertEquals("john: 4d 2d Ks 8d 4d (Winner)\n" +
				"jane: Th 3c Ks 8d 4d [folded]", round.results());
	}
	
	@Test
	public void when_both_players_see_all_the_cards_and_both_only_have_high_card_then_the_winner_should_be_the_one_with_the_highest_card() {
		PokerRound round = new PokerRound();
		round.deal("john", "4d", "2d");
		round.deal("jane", "Ah", "3c");
		round
			.dealFlop("Qc", "Td", "5s")
			.dealTurn("6c")
			.dealRiver("9h");
		
		Assert.assertEquals("john: 4d 2d Qc Td 5s 6c 9h\n" +
				"jane: Ah 3c Qc Td 5s 6c 9h (Winner)", round.results());
	}
}
