package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;
import ru.usu.cs.fun.back.TermsSubstitutor;

public class Inc extends Term {

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		int v = ((Int) arg.eval(scope, observer)).value;
		return new Int(v + 1);
	}

	@Override
	public Term substitute(String name, Term value) {
		return this;
	}

	@Override
	public String toString(TermsSubstitutor subst) {
		return "inc";
	}

}
