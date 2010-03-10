package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;

public class Add extends BinaryOperation {

	public Add() {
		super("+");
	}

	@Override
	protected Term calculate(Term arg1, Term arg2, Scope scope, EvalObserver observer) {
		int v1 = ((Int) arg1).value;
		int v2 = ((Int) arg2.eval(scope, observer)).value;
		return new Int(v1 + v2);
	}

}
