package ru.usu.cs.fun.front;

public class FunName extends BaseRecognizer {

	public FunName() {
		super("name");
	}

	@Override
	protected RecognizerState nextState(char ch) {
		boolean canReadMore = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ("+-*/%=!&|_~".indexOf(ch) >= 0)
				|| (ch >= '0' && ch <= '9');
		return canReadMore ? RecognizerState.finished : RecognizerState.dead;
	}
}
