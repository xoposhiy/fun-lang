package ru.usu.cs.fun.repl;

import java.io.IOException;
import java.io.Reader;

public class ConsoleReader extends Reader {

	private final FunConsole console;

	public ConsoleReader(FunConsole console) {
		this.console = console;
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int ch;
		int readed = 0;
		while ((readed < len) && ((ch = next()) >= 0)) {
			cbuf[off + readed] = (char) ch;
			readed++;
		}
		return readed;
	}

	private int next() {
		while ((line == null) || (pos >= line.length())) {
			line = console.readLine();
			if (line == null)
				return -1;
			pos = 0;
		}
		return line.charAt(pos++);
	}

	private String line = null;
	private int pos = 0;

}
