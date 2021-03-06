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
	        	.expect("john: 2h 2d As Qc Td 4s 2s [Three Of A Kind 2h 2d 2s Kicker(s) As Qc] (Winner)\n" +
	        			"jane: Ah 2c As Qc Td 4s 2s [Two Pair Ah As 2c 2s Kicker(s) Qc]") },

            { Round.scenario("highest three-of-a-kind wins", "john 2h 2d", "jane Ah Ac").deal("Qc Td 4s Ad 2s")
            	.expect("john: 2h 2d Qc Td 4s Ad 2s [Three Of A Kind 2h 2d 2s Kicker(s) Ad Qc]\n" +
            			"jane: Ah Ac Qc Td 4s Ad 2s [Three Of A Kind Ah Ac Ad Kicker(s) Qc Td] (Winner)") },

	        { Round.scenario("straight beats high card", "john 2h 3d", "jane Ah 9c").deal("6c 5d 4s Kc Th")
	        	.expect("john: 2h 3d 6c 5d 4s Kc Th [Straight 6c 5d 4s 3d 2h] (Winner)\n" +
	        			"jane: Ah 9c 6c 5d 4s Kc Th [High Card Kicker(s) Ah Kc Th 9c 6c]") },
	
	        { Round.scenario("straight beats pair", "john Kh 2c", "jane 2h 3d").deal("6c 5d 4s Kc Th")
	        	.expect("john: Kh 2c 6c 5d 4s Kc Th [Pair Kh Kc Kicker(s) Th 6c 5d]\n" +
	        			"jane: 2h 3d 6c 5d 4s Kc Th [Straight 6c 5d 4s 3d 2h] (Winner)") },
	
	        { Round.scenario("straight beats two-pair", "john 2h 3d", "jane 6h 4c").deal("6c 5d 4s Kc Th")
	        	.expect("john: 2h 3d 6c 5d 4s Kc Th [Straight 6c 5d 4s 3d 2h] (Winner)\n" +
	        			"jane: 6h 4c 6c 5d 4s Kc Th [Two Pair 6h 6c 4c 4s Kicker(s) Kc]") },
	
	        { Round.scenario("straight beats three-of-a-kind", "john 2h 3d", "jane 5h 5c").deal("6c 5d 4s Kc Th")
	        	.expect("john: 2h 3d 6c 5d 4s Kc Th [Straight 6c 5d 4s 3d 2h] (Winner)\n" +
	        			"jane: 5h 5c 6c 5d 4s Kc Th [Three Of A Kind 5h 5c 5d Kicker(s) Kc Th]") },
	
	        { Round.scenario("highest straight wins", "john 2h 3d", "jane 8h Ac").deal("6c 5d 4s 7c Th")
	        	.expect("john: 2h 3d 6c 5d 4s 7c Th [Straight 7c 6c 5d 4s 3d]\n" +
	        			"jane: 8h Ac 6c 5d 4s 7c Th [Straight 8h 7c 6c 5d 4s] (Winner)") },
	        
	        { Round.scenario("ace-low straight beats high card", "john Ac 2h", "jane Ah 9c").deal("3c 5d 4s Kc Th")
	        	.expect("john: Ac 2h 3c 5d 4s Kc Th [Straight 5d 4s 3c 2h Ac] (Winner)\n" +
	        			"jane: Ah 9c 3c 5d 4s Kc Th [High Card Kicker(s) Ah Kc Th 9c 5d]") },
	        	        
	        { Round.scenario("6-high straight beats ace-low", "john 7h Ac", "jane 6d 6c").deal("Kc 5d 4s 3c 2h")
	        	.expect("john: 7h Ac Kc 5d 4s 3c 2h [Straight 5d 4s 3c 2h Ac]\n" +
	        			"jane: 6d 6c Kc 5d 4s 3c 2h [Straight 6d 5d 4s 3c 2h] (Winner)") },
	        	        
	        { Round.scenario("ace-high straight beats ace-low straight", "john 2h 3c", "jane Kd Qc").deal("Ah 4d Js Th 5s")
	        	.expect("john: 2h 3c Ah 4d Js Th 5s [Straight 5s 4d 3c 2h Ah]\n" +
	        			"jane: Kd Qc Ah 4d Js Th 5s [Straight Ah Kd Qc Js Th] (Winner)") },
	        	        
	        { Round.scenario("low-to-high straight wrapping is not allowed", "john 2h 3c", "jane Jh Js").deal("Kd Qc Ah Th 5s")
	        	.expect("john: 2h 3c Kd Qc Ah Th 5s [High Card Kicker(s) Ah Kd Qc Th 5s]\n" +
	        			"jane: Jh Js Kd Qc Ah Th 5s [Straight Ah Kd Qc Jh Th] (Winner)") },
	        	        
	        { Round.scenario("hand with straight and three-of-a-kind should beat three-of-a-kind", "john 7h Ac", "jane 4d 6c").deal("3c 5d 3s 3h 2h")
	        	.expect("john: 7h Ac 3c 5d 3s 3h 2h [Three Of A Kind 3c 3s 3h Kicker(s) Ac 7h]\n" +
	        			"jane: 4d 6c 3c 5d 3s 3h 2h [Straight 6c 5d 4d 3c 2h] (Winner)") },
	        			
	        { Round.scenario("flush beats high card", "john 7h Ah", "jane 4d 6c").deal("2h 8h Tc Kh Js")
	        	.expect("john: 7h Ah 2h 8h Tc Kh Js [Flush Ah Kh 8h 7h 2h] (Winner)\n" +
	        			"jane: 4d 6c 2h 8h Tc Kh Js [High Card Kicker(s) Kh Js Tc 8h 6c]") },
	        			
	        { Round.scenario("flush beats pair", "john 7h Ac", "jane 4c 6c").deal("Ah 2c 8c Qc Kd")
	        	.expect("john: 7h Ac Ah 2c 8c Qc Kd [Pair Ac Ah Kicker(s) Kd Qc 8c]\n" +
	        			"jane: 4c 6c Ah 2c 8c Qc Kd [Flush Qc 8c 6c 4c 2c] (Winner)") },
	        			
	        { Round.scenario("flush beats two-pair", "john 7s Ah", "jane 4c 6c").deal("Ac 7c 8d Qc 2h")
	        	.expect("john: 7s Ah Ac 7c 8d Qc 2h [Two Pair Ah Ac 7s 7c Kicker(s) Qc]\n" +
	        			"jane: 4c 6c Ac 7c 8d Qc 2h [Flush Ac Qc 7c 6c 4c] (Winner)") },
	        			
	        { Round.scenario("flush beats three-of-a-kind", "john 2h 7h", "jane 4d 4s").deal("4h 2s 9h Ah Js")
	        	.expect("john: 2h 7h 4h 2s 9h Ah Js [Flush Ah 9h 7h 4h 2h] (Winner)\n" +
	        			"jane: 4d 4s 4h 2s 9h Ah Js [Three Of A Kind 4d 4s 4h Kicker(s) Ah Js]") },
	        			
	        { Round.scenario("flush beats straight", "john 5h Ac", "jane 4d Td").deal("3d 4c 2d 4h Kd")
	        	.expect("john: 5h Ac 3d 4c 2d 4h Kd [Straight 5h 4c 3d 2d Ac]\n" +
	        			"jane: 4d Td 3d 4c 2d 4h Kd [Flush Kd Td 4d 3d 2d] (Winner)") },
	        			
	        { Round.scenario("highest flush wins", "john 2c Kc", "jane Qc 3c").deal("4c 6c 8c 8h 8s")
	        	.expect("john: 2c Kc 4c 6c 8c 8h 8s [Flush Kc 8c 6c 4c 2c] (Winner)\n" +
	        			"jane: Qc 3c 4c 6c 8c 8h 8s [Flush Qc 8c 6c 4c 3c]") },
	        			
	        { Round.scenario("full house beats pair", "john 7h 7c", "jane 4d 6c").deal("7s Th Tc 2d Kd")
	        	.expect("john: 7h 7c 7s Th Tc 2d Kd [Full House 7h 7c 7s Th Tc] (Winner)\n" +
	        			"jane: 4d 6c 7s Th Tc 2d Kd [Pair Th Tc Kicker(s) Kd 7s 6c]") },
	        	        			
	        { Round.scenario("full house beats two-pair", "john 4d 4h", "jane 3d Tc").deal("3h 3c 9s 2h 9c")
	        	.expect("john: 4d 4h 3h 3c 9s 2h 9c [Two Pair 9s 9c 4d 4h Kicker(s) 3h]\n" +
	        			"jane: 3d Tc 3h 3c 9s 2h 9c [Full House 3d 3h 3c 9s 9c] (Winner)") },
	        	        			
	        { Round.scenario("full house beats three-of-a-kind", "john 7h Ah", "jane Kh Kc").deal("2h 2d Ks Ts 2c")
	        	.expect("john: 7h Ah 2h 2d Ks Ts 2c [Three Of A Kind 2h 2d 2c Kicker(s) Ah Ks]\n" +
	        			"jane: Kh Kc 2h 2d Ks Ts 2c [Full House Kh Kc Ks 2h 2d] (Winner)") },
	        	        			
	        { Round.scenario("full house beats straight", "john Ac Ah", "jane 4d 5c").deal("Ad 2s 3d 3c Th")
	        	.expect("john: Ac Ah Ad 2s 3d 3c Th [Full House Ac Ah Ad 3d 3c] (Winner)\n" +
	        			"jane: 4d 5c Ad 2s 3d 3c Th [Straight 5c 4d 3d 2s Ad]") },
	        	        			
	        { Round.scenario("full house beats flush", "john 7h 7c", "jane 4c 6c").deal("Tc Qc Ts 2c 7d")
	        	.expect("john: 7h 7c Tc Qc Ts 2c 7d [Full House 7h 7c 7d Tc Ts] (Winner)\n" +
	        			"jane: 4c 6c Tc Qc Ts 2c 7d [Flush Qc Tc 6c 4c 2c]") },
	        			
	        { Round.scenario("three-aces full house wins", "john 7h Ah", "jane 2s 9h").deal("2c Ac As 2d 9c")
	        	.expect("john: 7h Ah 2c Ac As 2d 9c [Full House Ah Ac As 2c 2d] (Winner)\n" +
	        			"jane: 2s 9h 2c Ac As 2d 9c [Full House 2s 2c 2d Ac As]") },
	        			
	        { Round.scenario("three-fours full house wins", "john 3d 3c", "jane 4d 4h").deal("Ah 4s Ac 3s 9h")
	        	.expect("john: 3d 3c Ah 4s Ac 3s 9h [Full House 3d 3c 3s Ah Ac]\n" +
	        			"jane: 4d 4h Ah 4s Ac 3s 9h [Full House 4d 4h 4s Ah Ac] (Winner)") },
    	        	        	        	        	        	        			
	        { Round.scenario("four-of-a-kind beats pair", "john 3d Ah", "jane Kd Kc").deal("2h 4c 6d Ks Kh")
	        	.expect("john: 3d Ah 2h 4c 6d Ks Kh [Pair Ks Kh Kicker(s) Ah 6d 4c]\n" +
	        			"jane: Kd Kc 2h 4c 6d Ks Kh [Four Of A Kind Kd Kc Ks Kh Kicker(s) 6d] (Winner)") },
	        			
	        { Round.scenario("four-of-a-kind beats two-pair", "john Kc Kd", "jane 2c 3d").deal("2h 4c 6d Ks Kh")
	        	.expect("john: Kc Kd 2h 4c 6d Ks Kh [Four Of A Kind Kc Kd Ks Kh Kicker(s) 6d] (Winner)\n" +
	        			"jane: 2c 3d 2h 4c 6d Ks Kh [Two Pair Ks Kh 2c 2h Kicker(s) 6d]") },
	        			
	        { Round.scenario("four-of-a-kind beats three-of-a-kind", "john Kc Ah", "jane 3c 9s").deal("3h 4c 3d 6h 3s")
	        	.expect("john: Kc Ah 3h 4c 3d 6h 3s [Three Of A Kind 3h 3d 3s Kicker(s) Ah Kc]\n" +
	        			"jane: 3c 9s 3h 4c 3d 6h 3s [Four Of A Kind 3c 3h 3d 3s Kicker(s) 9s] (Winner)") },
	        			
	        { Round.scenario("four-of-a-kind beats straight", "john 5s 5c", "jane 4h 6d").deal("3s 7h Kd 5d 5h")
	        	.expect("john: 5s 5c 3s 7h Kd 5d 5h [Four Of A Kind 5s 5c 5d 5h Kicker(s) Kd] (Winner)\n" +
	        			"jane: 4h 6d 3s 7h Kd 5d 5h [Straight 7h 6d 5d 4h 3s]") },
	        			
	        { Round.scenario("four-of-a-kind beats flush", "john Ah 3h", "jane Kd 2s").deal("Kh Ks 7h Kc 9h")
	        	.expect("john: Ah 3h Kh Ks 7h Kc 9h [Flush Ah Kh 9h 7h 3h]\n" +
	        			"jane: Kd 2s Kh Ks 7h Kc 9h [Four Of A Kind Kd Kh Ks Kc Kicker(s) 9h] (Winner)") },
	        			
	        { Round.scenario("four-of-a-kind beats full house", "john Ah Ac", "jane 9s 9d").deal("9c 2d As Ad 2c")
	        	.expect("john: Ah Ac 9c 2d As Ad 2c [Four Of A Kind Ah Ac As Ad Kicker(s) 9c] (Winner)\n" +
	        			"jane: 9s 9d 9c 2d As Ad 2c [Full House 9s 9d 9c As Ad]") },
	        			
	        { Round.scenario("highest four-of-a-kind wins", "john Th Ts", "jane Qc Qh").deal("Qd Td Tc 2s Qs")
	        	.expect("john: Th Ts Qd Td Tc 2s Qs [Four Of A Kind Th Ts Td Tc Kicker(s) Qd]\n" +
	        			"jane: Qc Qh Qd Td Tc 2s Qs [Four Of A Kind Qc Qh Qd Qs Kicker(s) Td] (Winner)") },
	        			
	        { Round.scenario("same four-of-a-kind, highest kicker wins", "john 3s 3h", "jane 4d 4c").deal("5h 5c 5d 5s 3c")
	        	.expect("john: 3s 3h 5h 5c 5d 5s 3c [Four Of A Kind 5h 5c 5d 5s Kicker(s) 3s]\n" +
	        			"jane: 4d 4c 5h 5c 5d 5s 3c [Four Of A Kind 5h 5c 5d 5s Kicker(s) 4d] (Winner)") },
	        	        			
	        { Round.scenario("straight-flush beats high card", "john 2h 3h", "jane 9d Ac").deal("4h 5h 6h Td Qs")
	        	.expect("john: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)\n" +
	        			"jane: 9d Ac 4h 5h 6h Td Qs [High Card Kicker(s) Ac Qs Td 9d 6h]") },
	        			
	        { Round.scenario("straight-flush beats pair", "john 2h 3h", "jane 6d Ac").deal("4h 5h 6h Td Qs")
	        	.expect("john: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)\n" +
	        			"jane: 6d Ac 4h 5h 6h Td Qs [Pair 6d 6h Kicker(s) Ac Qs Td]") },
	        			
	        { Round.scenario("straight-flush beats two pair", "john 4c 5s", "jane 2h 3h").deal("4h 5h 6h Td Qs")
	        	.expect("john: 4c 5s 4h 5h 6h Td Qs [Two Pair 5s 5h 4c 4h Kicker(s) Qs]\n" +
	        			"jane: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)") },
	        			
	        { Round.scenario("straight-flush beats three of a kind", "john 5c 2s", "jane 2h 3h").deal("4h 5h 6h 5d Qs")
	        	.expect("john: 5c 2s 4h 5h 6h 5d Qs [Three Of A Kind 5c 5h 5d Kicker(s) Qs 6h]\n" +
	        			"jane: 2h 3h 4h 5h 6h 5d Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)") },
	        			
	        { Round.scenario("straight-flush beats straight", "john 2h 3h", "jane 7d 8s").deal("4h 5h 6h Td Qs")
	        	.expect("john: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)\n" +
	        			"jane: 7d 8s 4h 5h 6h Td Qs [Straight 8s 7d 6h 5h 4h]") },
	        			
	        { Round.scenario("straight-flush beats flush", "john 2h 3h", "jane Ah Th").deal("4h 5h 6h Td Qs")
	        	.expect("john: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h] (Winner)\n" +
	        			"jane: Ah Th 4h 5h 6h Td Qs [Flush Ah Th 6h 5h 4h]") },
	        			
	        { Round.scenario("straight-flush beats full house", "john 5d 4s", "jane 2h 3h").deal("4h 5h 6h 6d 6s")
	        	.expect("john: 5d 4s 4h 5h 6h 6d 6s [Full House 6h 6d 6s 5d 5h]\n" +
	        			"jane: 2h 3h 4h 5h 6h 6d 6s [Straight Flush 6h 5h 4h 3h 2h] (Winner)") },
	        			
	        { Round.scenario("straight-flush beats four of a kind", "john 6c 4s", "jane 2h 3h").deal("4h 5h 6h 6d 6s")
	        	.expect("john: 6c 4s 4h 5h 6h 6d 6s [Four Of A Kind 6c 6h 6d 6s Kicker(s) 5h]\n" +
	        			"jane: 2h 3h 4h 5h 6h 6d 6s [Straight Flush 6h 5h 4h 3h 2h] (Winner)") },
	        			
	        { Round.scenario("highest straight-flush wins", "john 2h 3h", "jane 7h 8h").deal("4h 5h 6h Td Qs")
	        	.expect("john: 2h 3h 4h 5h 6h Td Qs [Straight Flush 6h 5h 4h 3h 2h]\n" +
	        			"jane: 7h 8h 4h 5h 6h Td Qs [Straight Flush 8h 7h 6h 5h 4h] (Winner)") },
        	
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
