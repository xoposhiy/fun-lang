package ru.usu.cs.fun.repl;

import ru.usu.cs.fun.engine.ASTNode;
import ru.usu.cs.fun.engine.ParsingListener;
import ru.usu.cs.fun.lang.FunScope;

public class REPLParsingListener implements ParsingListener {

	private final FunConsole console;
	private final FunScope scope;

	public REPLParsingListener(FunConsole console) {
		this.console = console;
		this.scope = new FunScope();
	}

	@Override
	public void parsedNode(ASTNode node) {
		Object res = node.execute(scope);
		console.write("--->" + res.toString());
	}

}
