package ru.usu.cs.fun.front;

public class FloatNumber extends AutomatonRecognizer {
	public FloatNumber() {
		super("float");
		setChars("0123456789+-.");
		setMaxState(4);
		addTransition(INITIAL_STATE, 1, "+-");
		addTransition(INITIAL_STATE, 2, "0123456789");
		addTransition(INITIAL_STATE, 3, ".");
		addTransition(1, 2, "0123456789");
		addTransition(1, 3, ".");
		addTransition(2, 2, "0123456789");
		addTransition(2, 3, ".");
		addTransition(3, 4, "0123456789");
		addTransition(4, 4, "0123456789");
		setFinalStates(2, 4);

	}
}
