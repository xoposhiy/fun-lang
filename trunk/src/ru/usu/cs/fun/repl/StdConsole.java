package ru.usu.cs.fun.repl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdConsole implements FunConsole {
	@Override
	public String readLine() {
		try {
			String line = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			if (line == null)
				return null;
			return line + "\r\n";
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(String s) {
		System.out.append(s);
	}

}
