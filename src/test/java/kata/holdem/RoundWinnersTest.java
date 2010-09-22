package kata.holdem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class RoundWinnersTest {
	@Test
	public void given_two_players_should_identify_highest_card_as_the_winner() {
		Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();
		
		playersAndTheirCards.put("player-one", Cards.from("3c"));
		playersAndTheirCards.put("player-two", Cards.from("2c"));
		
		RoundWinners winners = new RoundWinners(playersAndTheirCards);
		Assert.assertTrue(winners.isWinner("player-one"));
		Assert.assertFalse(winners.isWinner("player-two"));
	}

	@Test
	public void given_two_players_with_same_value_card_should_identify_both_as_the_winner() {
		Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();
		
		playersAndTheirCards.put("player-one", Cards.from("2d"));
		playersAndTheirCards.put("player-two", Cards.from("2c"));
		
		RoundWinners winners = new RoundWinners(playersAndTheirCards);
		Assert.assertTrue(winners.isWinner("player-one"));
		Assert.assertTrue(winners.isWinner("player-two"));
	}

	@Test
	public void given_one_player_with_a_low_pair_and_one_player_with_only_high_card_should_identify_pair_as_winner() {
		Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();
		
		playersAndTheirCards.put("player-one", Cards.from("2d", "2c"));
		playersAndTheirCards.put("player-two", Cards.from("Ah", "Kc"));
		
		RoundWinners winners = new RoundWinners(playersAndTheirCards);
		Assert.assertTrue(winners.isWinner("player-one"));
		Assert.assertFalse(winners.isWinner("player-two"));
	}

	@Test
	public void given_both_players_with_a_pair_should_identify_winner_with_the_highest_pair() {
		Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();
		
		playersAndTheirCards.put("pair-of-3s", Cards.from("2d", "3c", "8h", "3s", "Ad", "Th", "5s"));
		playersAndTheirCards.put("pair-of-10s", Cards.from("Tc", "Kc", "8h", "3s", "Ad", "Th", "5s"));
		
		RoundWinners winners = new RoundWinners(playersAndTheirCards);
		Assert.assertTrue(winners.isWinner("pair-of-10s"));
		Assert.assertFalse(winners.isWinner("pair-of-3s"));
	}

	@Test
	public void given_both_players_with_the_same_pair_should_identify_winner_with_the_highest_kicker() {
		Map<String, List<Card>> playersAndTheirCards = new HashMap<String, List<Card>>();
		
		playersAndTheirCards.put("7-kicker", Cards.from("2d", "7c", "5h", "3s", "Qd", "Th", "5s"));
		playersAndTheirCards.put("jack-kicker", Cards.from("2c", "Jc", "5h", "3s", "Qd", "Th", "5s"));
		
		RoundWinners winners = new RoundWinners(playersAndTheirCards);
		Assert.assertTrue(winners.isWinner("jack-kicker"));
		Assert.assertFalse(winners.isWinner("7-kicker"));
	}
}
