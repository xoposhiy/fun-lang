package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;
import ru.usu.cs.fun.back.TermsSubstitutor;

public class Eq extends Term {

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return new EqCont(arg);
	}

	@Override
	public String toString(TermsSubstitutor subst) {
		return "=";
	}

	// TODO Сделать так, чтобы подобные классы не приходилось плодить вручную.
	private static class EqCont extends Term {

		private Term arg1;
		private boolean evaluated = false;

		public EqCont(Term arg1) {
			this.arg1 = arg1;
		}

		@Override
		public String toString(TermsSubstitutor subst) {
			return "=" + subst.substitute(arg1).toString();
		}

		@Override
		public Term apply(Term arg, Scope scope, EvalObserver observer) {
			if (!evaluated) {
				arg1 = arg1.eval(scope, observer);
				evaluated = true;
			}
			return Bool.from(arg1.equals(arg.eval(scope, observer)));
		}
	}

}
