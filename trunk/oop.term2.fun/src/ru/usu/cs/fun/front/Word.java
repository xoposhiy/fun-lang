package ru.usu.cs.fun.front;

public class Word extends BaseRecognizer {

	private final String word;
	private int nextIndex;

	public Word(String lexemeType, String word) {
		super(lexemeType);
		this.word = word;
	}

	public Word(String word) {
		this(word, word);
	}

	@Override
	public void reset() {
		super.reset();
		nextIndex = 0;
	}

	@Override
	protected RecognizerState nextState(char ch) {
		if (nextIndex >= word.length() || word.charAt(nextIndex) != ch)
			return RecognizerState.dead;
		nextIndex++;
		return nextIndex == word.length() ? RecognizerState.finished : RecognizerState.alive;
	}

}
