package ru.usu.cs.fun.repl;

import junit.framework.Assert;

public class MockConsole implements FunConsole {

	private String[] text;
	private int currentLine = 0;
	private String expectedOuptut = "";
	private String actualOutput = "";

	public void setInput(String input) {
		if (input.isEmpty())
			this.text = new String[0];
		else
			this.text = input.split("\r\n");
	}

	public void expect(String output) {
		this.expectedOuptut += output;
	}

	public void checkExpectations() {
		Assert.assertEquals("console output", expectedOuptut, actualOutput);
		Assert.assertEquals("console input", currentLine, text.length);
	}

	public void write(String s) {
		actualOutput += s;
	}

	@Override
	public String readLine() {
		if (currentLine >= text.length)
			return null;
		return text[currentLine++];
	}

}
