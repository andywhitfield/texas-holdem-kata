package kata.holdem;

public enum Suit {
	h("Hearts"),
	c("Clubs"),
	d("Diamonds"),
	s("Spades");
	
	private final String displayName;
	private Suit(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
