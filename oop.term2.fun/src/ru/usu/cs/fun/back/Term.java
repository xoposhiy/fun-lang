package ru.usu.cs.fun.back;

public abstract class Term {

	public Term eval(Scope scope, EvalObserver observer) {
		return this;
	}

	public Term eval(Scope scope) {
		return eval(scope, EvalObserver.nullObserver);
	}

	public Term substitute(String name, Term value) {
		return this;
	}

	public abstract String toString(TermsSubstitutor substitutor);

	@Override
	public final String toString() {
		return toString(TermsSubstitutor.empty);
	}

	public boolean parenthesizeAsFun() {
		return false;
	}

	public boolean parenthesizeAsArg() {
		return false;
	}

	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return null;
	}

	public static Term bottom = new Bottom();

}
