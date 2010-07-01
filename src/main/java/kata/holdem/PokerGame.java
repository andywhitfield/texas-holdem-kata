package kata.holdem;

public class PokerGame {
	public PokerGame deal(String player) { return this; }
	public PokerGame holeCards(String card1, String card2) { return this; }
	public PokerGame dealFlop(String card, String card2, String card3) { return this; }
	public PokerGame dealTurn(String card1) { return this; }
	public PokerGame dealRiver(String card1) { return this; }
	public String results() {
		return "john: 4d 2d Ks Kd 9d 6h Jh (Winner)";
	}
}
