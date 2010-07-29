package kata.holdem.collections;

public interface Action<INPUT, OUTPUT> {
	public OUTPUT action(INPUT input);
}
