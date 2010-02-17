package ru.usu.cs.fun.back;

public class Abstraction extends Term {

	private final String argName;
	private final Term body;

	public Abstraction(String argName, Term body) {
		super();
		this.argName = argName;
		this.body = body;
	}

	@Override
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return body.substitute(argName, arg);
	}

	@Override
	public Term eval(Scope scope, EvalObserver observer) {
		return this;
	}

	@Override
	public Term substitute(String name, Term value) {
		if (name == argName)
			return this;
		String newArgName = argName;
		Term newBody = body;
		if (value != value.substitute(argName, Term.bottom)) {
			newArgName = newName();
			newBody = body.substitute(argName, new Variable(newArgName));
		}
		return new Abstraction(newArgName, newBody.substitute(name, value));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return argName.hashCode() + prime * body.hashCode();
	}

	@Override
	public boolean parenthesizeAsArg() {
		return true;
	}

	@Override
	public boolean parenthesizeAsFun() {
		return true;
	}

	private static String newName() {
		return "$" + lastGeneratedNameIndex++;
	}

	private static int lastGeneratedNameIndex = 0;

	@Override
	public String toString(TermsSubstitutor substitutor) {
		return "fun(" + argName + ") " + substitutor.substitute(body).toString();
		// return '\u03BB' + argName + "." +
		// substitutor.substitute(body).toString();
	}
}
