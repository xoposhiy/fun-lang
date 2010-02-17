package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;
import ru.usu.cs.fun.back.TermsSubstitutor;

public class Add extends Term {

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return new AddCont(arg);
	}

	private static class AddCont extends Term {

		private final Term arg1;

		public AddCont(Term arg1) {
			this.arg1 = arg1;
		}

		@Override
		public Term apply(Term arg2, Scope scope, EvalObserver observer) {
			int v1 = ((Int) arg1.eval(scope, observer)).value;
			int v2 = ((Int) arg2.eval(scope, observer)).value;
			return new Int(v1 + v2);
		}

		@Override
		public String toString(TermsSubstitutor subst) {
			return "+" + subst.substitute(arg1);
		}

	}

	@Override
	public String toString(TermsSubstitutor subst) {
		return "+";
	}

}
