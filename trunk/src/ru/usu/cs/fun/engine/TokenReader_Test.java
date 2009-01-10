package ru.usu.cs.fun.engine;

import java.io.BufferedReader;
import java.io.StringReader;

import junit.framework.TestCase;

public class TokenReader_Test extends TestCase {
	public void checkCurrent(String expectedValue, TokenReader reader) {
		assertEquals(expectedValue, reader.current().getValue());
		Token next = reader.next();
		assertEquals(next, reader.current());
	}

	public TokenReader createReader(String input) {
		return new TokenReader(new BufferedReader(new StringReader(input)));
	}

	public void testDot() {
		TokenReader reader = createReader("a.");
		checkCurrent("a", reader);
		checkCurrent(".", reader);
		assertNull(reader.next());
	}

	public void testBrackets() {
		TokenReader reader = createReader("a(b)");
		checkCurrent("a", reader);
		checkCurrent("(", reader);
		checkCurrent("b", reader);
		checkCurrent(")", reader);
		assertNull(reader.next());
	}

	public void testTailSpaces() {
		TokenReader reader = createReader("a  ");
		checkCurrent("a", reader);
		assertNull(reader.toString(), reader.current());
	}

	public void testCurrentOnEmptyInput() {
		TokenReader reader = createReader("");
		assertNull(reader.current());
	}

	public void testCurrentOnSpacesOnlyInput() {
		TokenReader reader = createReader("   ");
		assertNull(reader.toString(), reader.current());
	}

	public void testNextOnEmptyInput() {
		TokenReader reader = createReader("");
		assertNull(reader.next());
	}

	public void testNextOnOneLongToken() {
		TokenReader reader = createReader("abcd");
		checkCurrent("abcd", reader);
		assertNull(reader.next());
		assertNull(reader.current());
	}

	public void testNextOnSeveralTokens() {
		TokenReader reader = createReader("a b c");
		checkCurrent("a", reader);
		checkCurrent("b", reader);
		checkCurrent("c", reader);
		assertNull(reader.next());
	}

	public void testToStringInTheEndOfInput() {
		TokenReader reader = createReader("");
		assertEquals("<no more tokens>", reader.toString());
	}

	public void testToStringOnOneLexemeInput() {
		TokenReader reader = createReader("hello");
		assertEquals("<hello>", reader.toString());
	}

	public void testTwoTokensSeparatedByNewLineCharacter() {
		TokenReader reader = createReader("a\nb");
		assertEquals("a", reader.current().getValue());
		assertEquals("b", reader.next().getValue());
		assertNull(reader.next());
	}
}
