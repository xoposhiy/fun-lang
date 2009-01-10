package ru.usu.cs.fun.engine;

/**
 * “окен - это участок строки, представл€ющий лексическую единицу программы.
 * Parser работает с текстом программы, разбитым на токены.
 * ¬ будущем токен может хранить информацию о номере строки и позиции в строке.
 * 
 * @author pe
 */
public class Token {
	private String value;

	public Token(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "<" + value + ">";
	}

}
