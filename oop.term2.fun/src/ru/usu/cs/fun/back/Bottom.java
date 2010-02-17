package ru.usu.cs.fun.back;

public final class Bottom extends Term {

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return this;
	}

	@Override
	public String toString(TermsSubstitutor substitutor) {
		return "_|_";
	}

	@Override
	public Term eval(Scope scope, EvalObserver observer) {
		return this;
	}

	@Override
	public int hashCode() {
		return 8127;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Bottom;
	}

	@Override
	public Term substitute(String name, Term value) {
		return this;
	}
}