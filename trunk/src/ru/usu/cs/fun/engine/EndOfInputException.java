package ru.usu.cs.fun.engine;

public class EndOfInputException extends ParserException {
	private static final long serialVersionUID = 5454766627792978175L;

	public EndOfInputException() {
		super("unexpected end of input");
	}

}
