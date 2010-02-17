package ru.usu.cs.fun.front;

public class Space extends BaseRecognizer {

	public Space() {
		super("space", true);
	}

	@Override
	protected RecognizerState nextState(char ch) {
		boolean isSpace = ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
		return isSpace ? RecognizerState.finished : RecognizerState.dead;
	}

}
