package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;

public class Eq extends BinaryOperation {

	public Eq() {
		super("=");
	}

	@Override
	protected Term calculate(Term arg1, Term arg2, Scope scope, EvalObserver observer) {
		return Bool.from(arg1.equals(arg2.eval(scope, observer)));
	}

}
