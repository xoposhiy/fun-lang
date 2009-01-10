package ru.usu.cs.fun.engine;

import java.util.List;

import junit.framework.TestCase;

public class Parser_Test extends TestCase {
	public void testParseFunCall() throws ParserException {
		Parser parser = new Parser();
		TokenReader reader = TokenReader.createFromString("a(1 2).");
		List<ASTNode> ast = parser.parse(reader);
		assertEquals(1, ast.size());
		FunCallNode node = (FunCallNode) ast.get(0);
		assertEquals(FunCallNode.class, node.getClass());
		assertEquals("a", node.getFunName());
		List<FunCallNode> params = node.getArgs();
		assertEquals(2, params.size());
		FunCallNode arg1 = params.get(0);
		assertEquals("1", arg1.getFunName());
		assertEquals(0, arg1.getArgs().size());
		FunCallNode arg2 = params.get(1);
		assertEquals("2", arg2.getFunName());
		assertEquals(0, arg2.getArgs().size());
		assertNull(reader.current());
	}

	public void testParseFunDef() throws ParserException {
		Parser parser = new Parser();
		TokenReader reader = TokenReader
				.createFromString("fun add(a b) +(a b).");
		List<ASTNode> ast = parser.parse(reader);
		assertEquals(1, ast.size());
		FunDefNode node = (FunDefNode) ast.get(0);
		assertEquals("add", node.getFunName());
		assertEquals(2, node.getParams().size());
		FunCallNode body = node.getBody();
		assertEquals("+", body.getFunName());
		assertEquals(2, body.getArgs().size());
		assertNull(reader.current());

	}

	public void testParseFunDefWithoutArgs() throws ParserException {
		Parser parser = new Parser();
		TokenReader reader = TokenReader.createFromString("fun one 1.");
		List<ASTNode> ast = parser.parse(reader);
		assertEquals(1, ast.size());
		FunDefNode node = (FunDefNode) ast.get(0);
		assertEquals("one", node.getFunName());
		assertEquals(0, node.getParams().size());
		FunCallNode body = node.getBody();
		assertEquals("1", body.getFunName());
		assertEquals(0, body.getArgs().size());
		assertNull(reader.current());

	}

	public void testParseSimpleFunCall() throws ParserException {
		Parser parser = new Parser();
		List<ASTNode> ast = parser.parse(TokenReader.createFromString("a."));
		assertEquals(FunCallNode.class, ast.get(0).getClass());
		assertEquals(1, ast.size());
	}
}
