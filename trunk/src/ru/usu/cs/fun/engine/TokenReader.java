package ru.usu.cs.fun.engine;

import java.io.Reader;
import java.io.StringReader;

import ru.usu.cs.fun.utils.CanBeNull;

/**
 * Разбивает текст на токены. Семантика current/next.
 * 
 * @author pe
 */
public class TokenReader {

	public static TokenReader createFromString(String input) {
		return new TokenReader(new StringReader(input));
	}

	private final CharReader charReader;

	private Token currentToken;

	public TokenReader(Reader reader) {
		super();
		this.charReader = new CharReader(reader);
		next();
	}

	@CanBeNull
	public Token current() {
		return currentToken;
	}

	@CanBeNull
	public Token next() {
		currentToken = readToken();
		// System.out.println("LEXER -> " + currentToken);
		return currentToken;
	}

	@Override
	public String toString() {
		if (currentToken == null)
			return "<no more tokens>";
		return currentToken.toString();
	}

	private Token readToken() {
		if (charReader.current() == -1)
			return null;
		eatWhitespaces();
		if (charReader.current() == '(')
			return ensureToken('(');
		if (charReader.current() == ')')
			return ensureToken(')');
		if (charReader.current() == '.')
			return ensureToken('.');
		if (charReader.current() == -1)
			return null;
		StringBuilder s = new StringBuilder();
		while (!Character.isWhitespace(charReader.current())
				&& charReader.current() != '(' && charReader.current() != ')'
				&& charReader.current() != '.') {
			s.append((char) charReader.current());
			if (charReader.next() == -1)
				break;
		}

		return new Token(s.toString());
	}

	private void eatWhitespaces() {
		while (Character.isWhitespace(charReader.current()))
			charReader.next();
	}

	private Token ensureToken(char c) {
		charReader.next();
		return new Token(Character.toString(c));
	}

}
