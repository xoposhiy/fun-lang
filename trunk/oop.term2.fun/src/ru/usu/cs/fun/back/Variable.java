package ru.usu.cs.fun.back;

public class Variable extends Term {
	private final String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		Term evaluted = eval(scope, observer);
		if (evaluted == this)
			return null;
		return evaluted.apply(arg, scope, observer);
	}

	@Override
	public Term eval(Scope scope, EvalObserver observer) {
		Term result = scope.get(name);
		if (!result.equals(this))
			observer.onReduction(this, result);
		return result.eval(scope);
	}

	@Override
	public String toString(TermsSubstitutor subst) {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Variable)
			return name.equals(((Variable) obj).name);
		return false;
	}

	@Override
	public Term substitute(String name, Term value) {
		if (name.equals(this.name))
			return value;
		return this;
	}
}
