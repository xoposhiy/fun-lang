package ru.usu.cs.fun.front;

public abstract class BaseRecognizer implements Recognizer {

	protected RecognizerState state = RecognizerState.alive;
	protected final String lexemeType;
	private final boolean invisible;

	public BaseRecognizer(String lexemeType) {
		this(lexemeType, false);
	}

	public BaseRecognizer(String lexemeType, boolean invisible) {
		super();
		this.invisible = invisible;
		this.lexemeType = lexemeType;
	}

	@Override
	public RecognizerState currentState() {
		return state;
	}

	@Override
	public boolean invisible() {
		return invisible;
	}

	@Override
	public String getLexemeType() {
		return lexemeType;
	}

	@Override
	public void reset() {
		state = RecognizerState.alive;
	}

	@Override
	public RecognizerState takeChar(char ch) {
		if (state != RecognizerState.dead)
			state = nextState(ch);
		return state;
	}

	protected abstract RecognizerState nextState(char ch);

}