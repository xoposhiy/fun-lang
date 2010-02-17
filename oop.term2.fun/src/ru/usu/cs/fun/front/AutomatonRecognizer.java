package ru.usu.cs.fun.front;

public class AutomatonRecognizer extends BaseRecognizer {

	public static final int ERROR = -1;
	public static final int INITIAL_STATE = 0;

	private int[][] transitions;
	private boolean[] isFinal;
	private String knownChars;
	private int automataState;

	public AutomatonRecognizer(String lexemeType) {
		super(lexemeType);
	}

	public AutomatonRecognizer(String lexemeType, boolean invisible) {
		super(lexemeType, invisible);
	}

	@Override
	public void reset() {
		super.reset();
		automataState = INITIAL_STATE;
	}

	protected void setChars(String chars) {
		this.knownChars = chars;
	}

	protected void setMaxState(int maxState) {
		transitions = new int[maxState + 2][];
		for (int iFrom = 0; iFrom < transitions.length; iFrom++) {
			transitions[iFrom] = new int[knownChars.length() + 1];
			for (int ch = 0; ch < transitions[iFrom].length; ch++)
				transitions[iFrom][ch] = errorState();
		}
		isFinal = new boolean[statesCount()];
		isFinal[errorState()] = false;
	}

	private int statesCount() {
		return transitions.length;
	}

	@Override
	protected RecognizerState nextState(char ch) {
		automataState = transitions[automataState][getCharIndex(ch)];
		if (automataState == errorState())
			return RecognizerState.dead;
		if (isFinal[automataState])
			return RecognizerState.finished;
		return RecognizerState.alive;
	}

	private int errorState() {
		return transitions.length - 1;
	}

	protected void addTransition(int from, int to, String chars) {
		for (char ch : chars.toCharArray())
			transitions[from][getCharIndex(ch)] = to;
	}

	private int getCharIndex(char ch) {
		int ind = knownChars.indexOf(ch);
		if (ind >= 0)
			return ind;
		return knownChars.length();
	}

	protected void addTransition(int from, int to, boolean includeChars, String chars) {
		for (int i = 0; i < knownChars.length(); i++)
			if (chars.indexOf(knownChars.charAt(i)) < 0)
				transitions[from][i] = to;
		transitions[from][knownChars.length()] = to;
	}

	protected void setFinalStates(int... state) {
		for (int s : state) {
			isFinal[s] = true;
		}
	}
}
