package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.EvalObserver;
import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;
import ru.usu.cs.fun.back.TermsSubstitutor;

public abstract class BinaryOperation extends Term {

	private final String name;

	public BinaryOperation(String name) {
		super();
		this.name = name;
	}

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return new Cont(this, arg);
	}

	@Override
	public String toString(TermsSubstitutor substitutor) {
		return name;
	}

	protected abstract Term calculate(Term arg1, Term arg2, Scope scope, EvalObserver observer);

	private class Cont extends Term {

		private Term arg1;
		private boolean evaluated;
		private final BinaryOperation op;

		public Cont(BinaryOperation op, Term arg1) {
			this.op = op;
			this.arg1 = arg1;
		}

		@Override
		public Term apply(Term arg2, Scope scope, EvalObserver observer) {
			if (!evaluated) {
				arg1 = arg1.eval(scope, observer);
				evaluated = true;
			}
			return op.calculate(arg1, arg2, scope, observer);
		}

		@Override
		public String toString(TermsSubstitutor subst) {
			return op.name + ' ' + subst.substitute(arg1);
		}

	}
}
