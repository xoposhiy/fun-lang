package ru.usu.cs.fun.front;

public class Lexeme {
	private final String text;
	private final String type;

	public Lexeme(String text, String type) {
		this.text = text;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "(" + getText() + ", " + getType() + ")";
	}

}
