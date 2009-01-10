package ru.usu.cs.fun.engine;

import java.io.BufferedReader;
import java.io.StringReader;

import junit.framework.TestCase;

public class CharReader_Test extends TestCase {
	public CharReader createReader(String input) {
		return new CharReader(new BufferedReader(new StringReader(input)));
	}

	public void testCurrentCharAfterCreateIsFirstChar() {
		CharReader reader = createReader("hello");
		assertEquals('h', (char) reader.current());
	}

	public void testMultipleNext() {
		CharReader reader = createReader("h\ne");
		assertEquals('h', (char) reader.current());
		assertEquals('\n', (char) reader.next());
		assertEquals('e', (char) reader.next());
		assertEquals(-1, reader.next());
	}

	public void testNext() {
		CharReader reader = createReader("hello");
		assertEquals('e', (char) reader.next());
	}

	public void testNextOnTheEndOfLine() {
		CharReader reader = createReader("h\ne");
		assertEquals('\n', (char) reader.next());
	}

	public void testNoCurrentOnEmptyInput() {
		CharReader reader = createReader("");
		assertEquals(-1, reader.current());
	}

	public void testToString() {
		CharReader reader = createReader("hello");
		assertEquals("[h]", reader.toString());
	}

	public void testToStringOnEmptyInput() {
		CharReader reader = createReader("");
		assertEquals("[empty]", reader.toString());
	}

	public void testToStringOnSpace() {
		CharReader reader = createReader(" ");
		assertEquals("[ ]", reader.toString());
	}
}
