package ru.usu.cs.fun.engine;

import java.util.List;

import junit.framework.TestCase;

public class Fun_Test extends TestCase {
	private final Fun f42 = new Fun("42") {
		@Override
		protected Object calculate(Scope scope, List<Fun> args) {
			return 42;
		}
	};

	private final Fun inc = new Fun("inc") {
		@Override
		protected Object calculate(Scope scope, List<Fun> args) {
			return (Integer) args.get(0).execute(scope) + 1;
		}
	};

	public void testCurry() {
		assertEquals(43, inc.curry(0, f42).execute(null));
	}

	public void testExecuteConstant() {
		assertEquals(42, f42.execute(null));
	}
}
