package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class FunCallNode_Test extends TestCase {

	public void testExecuteConstant() {
		FunCallNode node = new FunCallNode("2");
		Object res = node.execute(new FakeScope());
		assertEquals(2, res);
	}

	public void testExecuteFunCall() {
		List<FunCallNode> list = new ArrayList<FunCallNode>();
		list.add(new FunCallNode("2"));
		list.add(new FunCallNode("2"));
		FunCallNode node = new FunCallNode("+", list);
		Object res = node.execute(new FakeScope());
		assertEquals(4, res);
	}

	public void testToString() {
		List<FunCallNode> params = new ArrayList<FunCallNode>();
		params.add(new FunCallNode("1"));
		FunCallNode node = new FunCallNode("name", params);
		assertEquals("name(1)", node.toString());
	}
}
