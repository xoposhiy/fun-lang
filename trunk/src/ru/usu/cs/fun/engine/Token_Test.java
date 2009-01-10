package ru.usu.cs.fun.engine;

import junit.framework.TestCase;

public class Token_Test extends TestCase {
	public void testToStringOfBracket() {
		assertEquals("<(>", new Token("(").toString());
	}

	public void testToStringOfName() {
		assertEquals("<name>", new Token("name").toString());
	}
}
