package ru.usu.cs.fun.repl;

import java.io.StringReader;
import java.util.List;

import ru.usu.cs.fun.engine.ASTNode;
import ru.usu.cs.fun.engine.EndOfInputException;
import ru.usu.cs.fun.engine.Parser;
import ru.usu.cs.fun.engine.ParserException;
import ru.usu.cs.fun.engine.Scope;
import ru.usu.cs.fun.engine.TokenReader;
import ru.usu.cs.fun.lang.FunScope;

public class REPL {

	private final FunConsole console;

	public REPL(FunConsole console) {
		this.console = console;
	}

	public static void main(String[] args) {
		new REPL(new StdConsole()).run();
	}

	public void run() {
		console.write("fun>");
		Parser p = new Parser();
		String line;
		Scope scope = new FunScope();
		while ((line = console.readLine()) != null) {
			List<ASTNode> nodes = tryParseMinimumInput(p, line);
			if (nodes != null) {
				for (ASTNode node : nodes) {
					try {
						Object result = node.execute(scope);
						console.write(result.toString() + "\r\n");
					} catch (Exception e) {
						console.write(e.getMessage() + "\r\n");
					}
				}
			}
			console.write("fun>");
		}
	}

	private List<ASTNode> tryParseMinimumInput(Parser p, String firstLine) {
		String input = firstLine;
		while (true) {
			try {
				return p.parse(new TokenReader(new StringReader(input)));
			} catch (EndOfInputException e) {
				console.write("...>");
				String line = console.readLine();
				if (line == null) {
					console.write(e.getMessage() + "\r\n");
					return null;
				}
				input = input + "\r\n" + line;
			} catch (ParserException e) {
				console.write(e.getMessage() + "\r\n");
				return null;
			}
		}
	}
}
