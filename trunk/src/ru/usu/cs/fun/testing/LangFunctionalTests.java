package ru.usu.cs.fun.testing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import junit.framework.TestCase;
import ru.usu.cs.fun.engine.ASTNode;
import ru.usu.cs.fun.engine.Parser;
import ru.usu.cs.fun.engine.ParserException;
import ru.usu.cs.fun.engine.Scope;
import ru.usu.cs.fun.engine.TokenReader;
import ru.usu.cs.fun.lang.FunScope;

public class LangFunctionalTests extends TestCase {
	private Scope scope;

	private List<ASTNode> parse(Reader r) throws ParserException {
		return new Parser().parse(new TokenReader(r));
	}

	@Override
	public void setUp() {
		scope = new FunScope();
	}

	public void testArithmetics() throws ParserException {
		runTestsSet("arithmetics");
	}

	public void testBooleans() throws ParserException {
		runTestsSet("booleans");
	}

	public void testIfStatement() throws ParserException {
		runTestsSet("ifs");
	}

	public void testRecursion() throws ParserException {
		runTestsSet("recursion");
	}

	public void testRelations() throws ParserException {
		runTestsSet("relations");
	}

	public void testUserDefinedFuns() throws ParserException {
		runTestsSet("funs");
	}

	private void runTestsSet(Reader r, Scope s) throws ParserException {
		for (ASTNode node : parse(r)) {
			try {
				Object result = node.execute(s);
				if (!(Boolean) result)
					throw new Exception(result.toString());
			} catch (Throwable e) {
				throw new RuntimeException(node.toString() + " -> "
						+ e.getMessage(), e);
			}
		}
	}

	private void runTestsSet(String testssetName) throws ParserException {
		InputStream stream = getClass().getResourceAsStream(
				"test-" + testssetName + ".txt");
		InputStreamReader r = new InputStreamReader(stream);
		runTestsSet(r, scope);
	}

}
