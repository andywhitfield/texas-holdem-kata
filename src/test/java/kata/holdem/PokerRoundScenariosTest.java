package kata.holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PokerRoundScenariosTest {
    @Parameterized.Parameters
    public static Collection<Object[]> rounds() {
        return Arrays.asList(new Object[][] {
        	{ Round.scenario("player folds", "john 4h 2d", "jane Th 3c").deal("Ks 8d 4d").fold("jane")
            	.expect("john: 4h 2d Ks 8d 4d [Pair 4h 4d Kicker(s) Ks 8d 2d] (Winner)\n" +
            			"jane: Th 3c Ks 8d 4d [folded]") },
            	
            { Round.scenario("high card", "john 4d 2d", "jane Ah 3c").deal("Qc Td 5s 6c 9h")
            	.expect("john: 4d 2d Qc Td 5s 6c 9h [High Card Kicker(s) Qc Td 9h 6c 5s]\n" +
            			"jane: Ah 3c Qc Td 5s 6c 9h [High Card Kicker(s) Ah Qc Td 9h 6c] (Winner)") },

            { Round.scenario("pair over high card", "john 4d 2d", "jane Ah 3c").deal("Qc Td 4s 6c 9h")
            	.expect("john: 4d 2d Qc Td 4s 6c 9h [Pair 4d 4s Kicker(s) Qc Td 9h] (Winner)\n" +
            			"jane: Ah 3c Qc Td 4s 6c 9h [High Card Kicker(s) Ah Qc Td 9h 6c]") },

            { Round.scenario("three-of-a-kind beats high card", "john 2h 2d", "jane Ah 3c").deal("2c Td 4s 6c 9h")
            	.expect("john: 2h 2d 2c Td 4s 6c 9h [Three Of A Kind 2h 2d 2c Kicker(s) Td 9h] (Winner)\n" +
            			"jane: Ah 3c 2c Td 4s 6c 9h [High Card Kicker(s) Ah Td 9h 6c 4s]") },

            { Round.scenario("three-of-a-kind beats pair", "john Ah 2c", "jane 2h 2d").deal("Td 4s 6c 9h 2s")
            	.expect("john: Ah 2c Td 4s 6c 9h 2s [Pair 2c 2s Kicker(s) Ah Td 9h]\n" +
            			"jane: 2h 2d Td 4s 6c 9h 2s [Three Of A Kind 2h 2d 2s Kicker(s) Td 9h] (Winner)") },

	        { Round.scenario("three-of-a-kind beats two-pair", "john 2h 2d", "jane Ah 2c").deal("As Qc Td 4s 2s")
	        	.expect("john: 2h 2d As Qc Td 4s 2s [Three Of A Kind 2h 2d 2s Kicker(s) As Qc Td] (Winner)\n" +
	        			"jane: Ah 2c As Qc Td 4s 2s [Two Pair Ah As 2c 2s Kicker(s) Qc]") },

            { Round.scenario("highest three-of-a-kind wins", "john 2h 2d", "jane Ah Ac").deal("Qc Td 4s Ad 2s")
            	.expect("john: 2h 2d Qc Td 4s Ad 2s [Three Of A Kind 2h 2d 2s Kicker(s) Ad Qc]\n" +
            			"jane: Ah Ac Qc Td 4s Ad 2s [Three Of A Kind Ah Ac Ad Kicker(s) Qc Td] (Winner)") }
        });
    }

    private final Round round;
    public PokerRoundScenariosTest(Round round) {
        this.round = round;
    }

    @Test
    public void verify_rounds() {
    	Assert.assertEquals(round.getDescription(), round.getExpectedResult(), round.getRound().toString());
    }
    
    private static class Round {
    	public static Round scenario(String scenarioDescription, String... playersAndHoleCards) {
    		List<String> players = new ArrayList<String>();
    		List<String[]> playersHoleCard = new ArrayList<String[]>();
    		for (String playerAndHoleCards : playersAndHoleCards) {
    			String[] playerAndHoleCardsSplit = playerAndHoleCards.split(" ");
    			playersHoleCard.add(playerAndHoleCardsSplit);
    			players.add(playerAndHoleCardsSplit[0]);
    		}
    		Round round = new Round(scenarioDescription, players.toArray(new String[0]));
    		for (String[] playerAndHoleCards : playersHoleCard)
    			round.getRound().deal(playerAndHoleCards[0], playerAndHoleCards[1], playerAndHoleCards[2]);
    		return round;
    	}
    	
    	private String scenarioDescription;
    	private PokerRound round;
    	private boolean dealtTurn = false;
    	private String expectedResult;
    	
    	private Round(String scenarioDescription, String[] players) {
    		this.scenarioDescription = scenarioDescription;
    		round = new PokerGame(players).newRound();
    	}
    	
    	public PokerRound getRound() { return round; }
    	public String getDescription() { return scenarioDescription; }
    	
    	public Round deal(String cards) {
    		String[] card = cards.split(" ");
    		if (card.length == 3) round.dealFlop(card[0], card[1], card[2]);
    		else if (card.length == 5) round.dealFlop(card[0], card[1], card[2]).dealTurn(card[3]).dealRiver(card[4]);
    		else if (dealtTurn) round.dealRiver(cards);
    		else { round.dealTurn(cards); dealtTurn = true; }
    		return this;
    	}
    	
    	public Round fold(String player) {
    		round.fold(player);
    		return this;
    	}
    	
    	public Round expect(String result) {
    		expectedResult = result;
    		return this;
    	}
    	
    	public String getExpectedResult() { return expectedResult; }
    }
}
