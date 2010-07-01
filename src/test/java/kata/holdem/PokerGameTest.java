package kata.holdem;

import org.junit.Assert;
import org.junit.Test;

public class PokerGameTest {
	@Test
	public void when_there_is_only_one_player_then_his_hand_should_be_the_winner() {
		PokerGame game = new PokerGame();
		game.deal("john").holeCards("4d", "2d");
		game.dealFlop("Ks", "Kd", "9d");
		game.dealTurn("6h");
		game.dealRiver("Jh");
		
		Assert.assertEquals("john: 4d 2d Ks Kd 9d 6h Jh (Winner)", game.results());
	}
}
