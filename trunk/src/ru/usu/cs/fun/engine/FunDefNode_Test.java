package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

import ru.usu.cs.fun.utils.Listof;

import junit.framework.TestCase;

public class FunDefNode_Test extends TestCase {
	public void testDefineFun() {
		FunDefNode node = new FunDefNode("name", Listof.strings(),
				new FunCallNode("1"));
		Scope scope = new FakeScope();
		node.execute(scope);
		assertNotNull(scope.findFun("name"));
	}

	public void testExecuteDefinedFun() {
		FunDefNode node = new FunDefNode("id", Listof.strings("x"),
				new FunCallNode("x"));
		Scope scope = new FakeScope();
		node.execute(scope);
		ArrayList<FunCallNode> args = new ArrayList<FunCallNode>();
		args.add(new FunCallNode("1"));
		FunCallNode callnode = new FunCallNode("id", args);
		assertEquals(1, callnode.execute(scope));
	}

	public void testToString() {
		List<String> params = new ArrayList<String>();
		params.add("x");
		FunDefNode node = new FunDefNode("name", params, new FunCallNode("1"));
		assertEquals("fun name(x) 1", node.toString());
	}
}
