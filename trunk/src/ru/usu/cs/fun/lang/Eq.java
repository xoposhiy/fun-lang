package ru.usu.cs.fun.lang;

import java.util.List;

import ru.usu.cs.fun.engine.Fun;
import ru.usu.cs.fun.engine.Scope;

public class Eq extends Fun {
	public Eq() {
		super("=");
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		return args.get(0).execute(scope).equals(args.get(1).execute(scope));
	}
}
