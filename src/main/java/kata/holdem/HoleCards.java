package kata.holdem;

class HoleCards {
	private final Card holeCard1;
	private final Card holeCard2;
	
	public HoleCards(Card holeCard1, Card holeCard2) {
		this.holeCard1 = holeCard1;
		this.holeCard2 = holeCard2;
	}
	
	public Card getHoleCard1() { return holeCard1; }
	public Card getHoleCard2() { return holeCard2; }

	public String cardsSummary() {
		return getHoleCard1().toString() + " " + getHoleCard2();
	}
}
