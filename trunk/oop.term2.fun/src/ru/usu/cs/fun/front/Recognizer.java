package ru.usu.cs.fun.front;

public interface Recognizer {
	public boolean invisible();

	public RecognizerState takeChar(char ch);

	public RecognizerState currentState();

	public void reset();

	public String getLexemeType();
}
