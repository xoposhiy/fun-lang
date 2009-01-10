package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser преобразует последовательность токенов в узлы абстрактного
 * синтаксического дерева программы (ASTNode). Узлы - это то, что можно
 * выполнять.
 * 
 * @author pe
 */
public class Parser {
	public void parse(TokenReader reader, ParsingListener listener)
			throws ParserException {
		while (reader.current() != null) {
			if (reader.current().getValue().equals("fun"))
				listener.parsedNode(readFunDef(reader));
			else
				listener.parsedNode(readFunCall(reader, true));

		}
	}

	public List<ASTNode> parse(TokenReader reader) throws ParserException {
		CollectingListener listener = new CollectingListener();
		parse(reader, listener);
		return listener.getNodes();
	}

	/**
	 * Читает вызов функции из reader.
	 * 
	 * @throws ParserException
	 */
	private FunCallNode readFunCall(TokenReader reader, boolean checkTailingDot)
			throws ParserException {
		List<FunCallNode> actualParams = new ArrayList<FunCallNode>();
		String funName = readName(reader);
		Token currentToken = reader.current();
		if (currentToken != null) {
			String t = currentToken.getValue();
			if (t.equals("(")) {
				Token next = reader.next();
				if (next == null)
					throw new EndOfInputException();
				String value = next.getValue();
				while (!value.equals(")")) {
					actualParams.add(readFunCall(reader, false));
					value = reader.current().getValue();
				}
				reader.next();
			}
		}
		if (checkTailingDot)
			readTailingDot(reader);
		return new FunCallNode(funName, actualParams);
	}

	private void readTailingDot(TokenReader reader) throws ParserException {
		if (reader.current() == null)
			throw new EndOfInputException();
		if (!reader.current().getValue().equals("."))
			throw new ParserException("tailing dot <.> expected but "
					+ reader.current() + " found");
		reader.next();
	}

	/**
	 * Читает определение функции из reader.
	 * 
	 * @throws ParserException
	 */
	private ASTNode readFunDef(TokenReader reader) throws ParserException {
		List<String> params = new ArrayList<String>();
		reader.next();
		String funName = readName(reader);
		if (reader.current() == null)
			throw new EndOfInputException();
		String t = reader.current().getValue();
		if (t.equals("(")) {
			reader.next();
			while (!t.equals(")")) {
				t = readName(reader);
				params.add(t);
				t = reader.current().getValue();
			}
			reader.next();
		}

		FunCallNode body = readFunCall(reader, true);
		return new FunDefNode(funName, params, body);
	}

	private String readName(TokenReader reader) {
		Token t = reader.current();
		if (t.getValue().equals("(") || t.getValue().equals(")"))
			throw new RuntimeException("unexpected " + t.toString());
		reader.next();
		return t.getValue();
	}

}
