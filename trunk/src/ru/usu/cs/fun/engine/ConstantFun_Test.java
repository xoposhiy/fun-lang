package ru.usu.cs.fun.engine;

import junit.framework.TestCase;

public class ConstantFun_Test extends TestCase {
	public void testExecuteNumber() {
		Fun f = new ConstantFun(42);
		assertEquals(42, f.execute(null));
	}
}
