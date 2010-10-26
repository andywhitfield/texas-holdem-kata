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
                { Round.scenario("player folds", "john 4d 2d", "jane Th 3c").deal("Ks 8d 4d").fold("jane")
                	.expect("john: 4d 2d Ks 8d 4d (Winner)\njane: Th 3c Ks 8d 4d [folded]") }
        });
    }

    private final Round round;
    public PokerRoundScenariosTest(Round round) {
        this.round = round;
    }

    @Test
    public void verify_rounds() {
    	Assert.assertEquals(round.getDescription(), round.getExpectedResult(), round.getRound().results());
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
